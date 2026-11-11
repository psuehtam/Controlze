package com.grupo.controlze.ui.ranking

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grupo.controlze.data.local.RankingEntity
import com.grupo.controlze.data.repository.RankingRepository
import kotlinx.coroutines.launch

class RankingViewModel(private val repository: RankingRepository) : ViewModel() {
    var uiState by mutableStateOf(RankingUiState())
        private set

    private var todos: List<RankingEntity> = emptyList()

    init {
        viewModelScope.launch {
            repository.ranking.collect { lista ->
                todos = lista
                atualizarLista()
            }
        }
    }

    fun buscar(texto: String) {
        uiState = uiState.copy(busca = texto)
        atualizarLista()
    }

    fun selecionarOrdenacao(ordenacao: OrdenacaoRanking) {
        uiState = uiState.copy(ordenacao = ordenacao)
        atualizarLista()
    }

    fun abrirEdicao(item: RankingEntity) {
        uiState = uiState.copy(editando = item, nomeEditado = item.nomeJogador, mensagem = null)
    }

    fun mudarNomeEditado(texto: String) {
        uiState = uiState.copy(nomeEditado = texto)
    }

    fun fecharEdicao() {
        uiState = uiState.copy(editando = null, nomeEditado = "")
    }

    fun salvarNome() {
        val item = uiState.editando ?: return
        val nome = uiState.nomeEditado.trim()
        if (nome.isBlank()) {
            uiState = uiState.copy(mensagem = "Informe um nome para salvar.")
            return
        }
        viewModelScope.launch {
            val erro = repository.atualizar(item.copy(nomeJogador = nome))
            uiState = uiState.copy(editando = null, nomeEditado = "", mensagem = erro)
        }
    }

    fun pedirExclusao(item: RankingEntity?) {
        uiState = uiState.copy(confirmarExclusao = item)
    }

    fun excluirConfirmado() {
        val item = uiState.confirmarExclusao ?: return
        viewModelScope.launch {
            val erro = repository.excluir(item)
            uiState = uiState.copy(confirmarExclusao = null, mensagem = erro)
        }
    }

    fun pedirApagarTudo(mostrar: Boolean) {
        uiState = uiState.copy(confirmarApagarTudo = mostrar)
    }

    fun apagarTudo() {
        viewModelScope.launch {
            val erro = repository.excluirTodos()
            uiState = uiState.copy(confirmarApagarTudo = false, mensagem = erro)
        }
    }

    private fun atualizarLista() {
        uiState = uiState.copy(
            itens = filtrarEOrdenarRanking(todos, uiState.busca, uiState.ordenacao)
        )
    }
}
