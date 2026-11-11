package com.grupo.controlze.ui.game

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grupo.controlze.data.local.RankingEntity
import com.grupo.controlze.data.repository.FaseRepository
import com.grupo.controlze.data.repository.RankingRepository
import com.grupo.controlze.logic.TipoFase
import com.grupo.controlze.logic.escolhaCerta
import com.grupo.controlze.logic.palavraCerta
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class GameViewModel(
    private val faseRepository: FaseRepository,
    private val rankingRepository: RankingRepository
) : ViewModel() {
    var uiState by mutableStateOf(GameUiState())
        private set
    private var nomeJogador = "Jogador"
    private var contagem: Job? = null

    init {
        viewModelScope.launch {
            val erro = faseRepository.sincronizar()
            if (erro != null) uiState = uiState.copy(mensagem = erro)
        }
        viewModelScope.launch {
            faseRepository.fases.collect { lista ->
                uiState = uiState.copy(
                    fasesDisponiveis = lista.sortedBy { fase -> fase.ordem },
                    carregando = false
                )
            }
        }
    }

    fun carregarOriginais() {
        viewModelScope.launch {
            val erro = faseRepository.carregarOriginais()
            uiState = uiState.copy(mensagem = erro)
        }
    }

    fun iniciar(nome: String) {
        contagem?.cancel()
        contagem = null
        nomeJogador = nome.trim().ifBlank { "Jogador" }
        uiState = uiState.copy(
            partida = uiState.fasesDisponiveis,
            emAndamento = uiState.fasesDisponiveis.isNotEmpty(),
            indiceAtual = 0,
            erros = 0,
            inicioMillis = System.currentTimeMillis(),
            venceu = false,
            tempoFinalSegundos = 0L,
            mensagem = null,
            segundosEspera = 0
        )
        prepararFase()
    }

    fun escolheu(texto: String) {
        val fase = uiState.faseAtual ?: return
        if (!uiState.emAndamento) return
        if (escolhaCerta(fase, texto)) acertou() else errou()
    }

    fun tocouPalavra(palavra: String) {
        val fase = uiState.faseAtual ?: return
        if (!uiState.emAndamento) return
        if (palavraCerta(fase, palavra)) acertou() else errou()
    }

    private fun acertou() {
        val estado = uiState
        if (!estado.emAndamento) return
        if (estado.indiceAtual == estado.partida.lastIndex) {
            vencer(estado)
        } else {
            uiState = uiState.copy(
                indiceAtual = uiState.indiceAtual + 1,
                mensagem = null,
                segundosEspera = 0
            )
            prepararFase()
        }
    }

    private fun errou() {
        if (!uiState.emAndamento) return
        contagem?.cancel()
        contagem = null
        uiState = uiState.copy(
            indiceAtual = 0,
            erros = uiState.erros + 1,
            mensagem = "Errou! Volta pro começo.",
            segundosEspera = 0
        )
        prepararFase()
    }

    private fun prepararFase() {
        contagem?.cancel()
        contagem = null
        val fase = uiState.faseAtual ?: return
        if (!uiState.emAndamento || fase.tipo != TipoFase.ESPERAR) return

        contagem = viewModelScope.launch {
            for (segundos in 5 downTo 1) {
                if (!uiState.emAndamento || uiState.faseAtual?.id != fase.id) return@launch
                uiState = uiState.copy(segundosEspera = segundos)
                delay(1_000)
            }
            if (uiState.emAndamento && uiState.faseAtual?.id == fase.id) {
                contagem = null
                uiState = uiState.copy(segundosEspera = 0)
                acertou()
            }
        }
    }

    fun tocouDuranteEspera() {
        val fase = uiState.faseAtual ?: return
        if (!uiState.emAndamento || fase.tipo != TipoFase.ESPERAR) return
        errou()
    }

    private fun vencer(estado: GameUiState) {
        contagem?.cancel()
        contagem = null
        val segundos = (System.currentTimeMillis() - estado.inicioMillis) / 1000
        uiState = uiState.copy(
            emAndamento = false,
            venceu = true,
            tempoFinalSegundos = segundos,
            segundosEspera = 0
        )
        viewModelScope.launch {
            val erro = rankingRepository.inserir(
                RankingEntity(
                    nomeJogador = nomeJogador,
                    totalFases = estado.partida.size,
                    erros = estado.erros,
                    tempoSegundos = segundos
                )
            )
            if (erro != null) uiState = uiState.copy(mensagem = erro)
        }
    }

    override fun onCleared() {
        contagem?.cancel()
        super.onCleared()
    }
}
