package com.grupo.controlze.ui.game

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grupo.controlze.data.local.RankingEntity
import com.grupo.controlze.data.repository.FaseRepository
import com.grupo.controlze.data.repository.RankingRepository
import com.grupo.controlze.logic.escolhaCerta
import kotlinx.coroutines.launch

class GameViewModel(
    private val faseRepository: FaseRepository,
    private val rankingRepository: RankingRepository
) : ViewModel() {
    var uiState by mutableStateOf(GameUiState())
        private set
    private var nomeJogador = "Jogador"

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
        nomeJogador = nome.trim().ifBlank { "Jogador" }
        uiState = uiState.copy(
            partida = uiState.fasesDisponiveis,
            emAndamento = uiState.fasesDisponiveis.isNotEmpty(),
            indiceAtual = 0,
            erros = 0,
            inicioMillis = System.currentTimeMillis(),
            venceu = false,
            tempoFinalSegundos = 0L,
            mensagem = null
        )
    }

    fun escolheu(texto: String) {
        val fase = uiState.faseAtual ?: return
        if (!uiState.emAndamento) return
        if (escolhaCerta(fase, texto)) acertou() else errou()
    }

    private fun acertou() {
        val estado = uiState
        if (!estado.emAndamento) return
        if (estado.indiceAtual == estado.partida.lastIndex) {
            vencer(estado)
        } else {
            uiState = uiState.copy(indiceAtual = uiState.indiceAtual + 1, mensagem = null)
        }
    }

    private fun errou() {
        uiState = uiState.copy(
            indiceAtual = 0,
            erros = uiState.erros + 1,
            mensagem = "Errou! Volta pro começo."
        )
    }

    private fun vencer(estado: GameUiState) {
        val segundos = (System.currentTimeMillis() - estado.inicioMillis) / 1000
        uiState = uiState.copy(
            emAndamento = false,
            venceu = true,
            tempoFinalSegundos = segundos
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
}
