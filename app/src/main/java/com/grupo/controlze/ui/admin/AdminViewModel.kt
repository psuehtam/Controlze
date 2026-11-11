package com.grupo.controlze.ui.admin

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grupo.controlze.data.repository.FaseRepository
import com.grupo.controlze.logic.Fase
import com.grupo.controlze.logic.TipoFase
import com.grupo.controlze.logic.erroNaFaseCriada
import com.grupo.controlze.logic.ordemAoMover
import com.grupo.controlze.logic.ordemParaNovaFase
import kotlinx.coroutines.launch

class AdminViewModel(private val repository: FaseRepository) : ViewModel() {
    var uiState by mutableStateOf(AdminUiState())
        private set

    private var todas: List<Fase> = emptyList()

    init {
        viewModelScope.launch {
            repository.fases.collect { fases ->
                todas = fases.sortedBy { it.ordem }
                atualizarLista()
            }
        }
    }

    fun mudarFiltro(filtro: FiltroPainel) {
        uiState = uiState.copy(filtro = filtro)
        atualizarLista()
    }

    fun carregarOriginais() {
        viewModelScope.launch {
            val erro = repository.carregarOriginais()
            uiState = uiState.copy(mensagem = erro ?: "Fases originais carregadas.")
        }
    }

    fun abrirFormulario(id: String) {
        val formulario = when {
            id == "nova" -> Formulario()
            else -> {
                val fase = todas.find { it.id == id }
                when {
                    fase == null -> {
                        uiState = uiState.copy(
                            formulario = Formulario(),
                            edicaoBloqueada = true,
                            mensagem = "Fase não encontrada."
                        )
                        return
                    }
                    fase.original -> {
                        uiState = uiState.copy(
                            formulario = Formulario(),
                            edicaoBloqueada = true,
                            mensagem = "Fases originais são bloqueadas."
                        )
                        return
                    }
                    else -> Formulario(
                        id = fase.id,
                        ordem = fase.ordem,
                        pergunta = fase.pergunta,
                        alternativas = fase.alternativas.take(4) +
                            List((4 - fase.alternativas.size).coerceAtLeast(0)) { "" },
                        indiceCerto = fase.alternativas.indexOf(fase.resposta)
                    )
                }
            }
        }
        uiState = uiState.copy(
            formulario = formulario,
            edicaoBloqueada = false,
            mensagem = null
        )
    }

    fun mudarPergunta(texto: String) {
        uiState = uiState.copy(formulario = uiState.formulario.copy(pergunta = texto))
    }

    fun mudarAlternativa(posicao: Int, texto: String) {
        if (posicao !in 0..3) return
        val alternativas = uiState.formulario.alternativas.toMutableList()
        alternativas[posicao] = texto
        uiState = uiState.copy(formulario = uiState.formulario.copy(alternativas = alternativas))
    }

    fun marcarCerta(posicao: Int) {
        if (posicao !in 0..3) return
        uiState = uiState.copy(formulario = uiState.formulario.copy(indiceCerto = posicao))
    }

    fun salvar(aoTerminar: () -> Unit) {
        if (uiState.edicaoBloqueada) return
        val formulario = uiState.formulario
        val alternativas = formulario.alternativas.map { it.trim() }
        val erro = erroNaFaseCriada(formulario.pergunta, alternativas, formulario.indiceCerto)
        if (erro != null) {
            uiState = uiState.copy(mensagem = erro)
            return
        }

        val fase = Fase(
            id = formulario.id ?: "criada-${System.currentTimeMillis()}",
            ordem = formulario.ordem ?: ordemParaNovaFase(todas),
            categoria = "Criada",
            tipo = TipoFase.MULTIPLA_ESCOLHA,
            pergunta = formulario.pergunta.trim(),
            alternativas = alternativas,
            resposta = alternativas[formulario.indiceCerto],
            original = false
        )
        uiState = uiState.copy(salvando = true, mensagem = null)
        viewModelScope.launch {
            val erroAoSalvar = repository.salvarCriada(fase)
            uiState = uiState.copy(salvando = false, mensagem = erroAoSalvar)
            if (erroAoSalvar == null) {
                uiState = uiState.copy(formulario = Formulario())
                aoTerminar()
            }
        }
    }

    fun mover(fase: Fase, paraCima: Boolean) {
        val faseAtual = todas.find { it.id == fase.id }
        if (faseAtual == null) {
            uiState = uiState.copy(mensagem = "Fase não encontrada.")
            return
        }
        if (faseAtual.original || faseAtual.id.startsWith("original-")) {
            uiState = uiState.copy(mensagem = "Fases originais não podem ser movidas.")
            return
        }

        val indice = todas.indexOfFirst { it.id == faseAtual.id }
        val novaOrdem = ordemAoMover(todas, indice, paraCima)
        if (novaOrdem == null) {
            uiState = uiState.copy(mensagem = "Essa fase não pode ir para lá.")
            return
        }

        viewModelScope.launch {
            val erro = repository.salvarCriada(faseAtual.copy(ordem = novaOrdem))
            uiState = uiState.copy(mensagem = erro ?: "Fase movida.")
        }
    }

    fun pedirExclusao(fase: Fase?) {
        if (fase == null) {
            uiState = uiState.copy(confirmarExclusao = null)
            return
        }
        val faseAtual = todas.find { it.id == fase.id }
        if (faseAtual == null) {
            uiState = uiState.copy(mensagem = "Fase não encontrada.")
            return
        }
        if (faseAtual.original || faseAtual.id.startsWith("original-")) {
            uiState = uiState.copy(mensagem = "Fases originais não podem ser excluídas.")
            return
        }
        uiState = uiState.copy(confirmarExclusao = faseAtual)
    }

    fun excluirConfirmada() {
        val selecionada = uiState.confirmarExclusao ?: return
        uiState = uiState.copy(confirmarExclusao = null)
        val faseAtual = todas.find { it.id == selecionada.id }
        if (faseAtual == null) {
            uiState = uiState.copy(mensagem = "Fase não encontrada.")
            return
        }
        if (faseAtual.original || faseAtual.id.startsWith("original-")) {
            uiState = uiState.copy(mensagem = "Fases originais não podem ser excluídas.")
            return
        }

        viewModelScope.launch {
            val erro = repository.excluirCriada(faseAtual)
            uiState = uiState.copy(mensagem = erro ?: "Fase excluída.")
        }
    }

    private fun atualizarLista() {
        uiState = uiState.copy(itens = itensDoPainel(todas, uiState.filtro))
    }
}
