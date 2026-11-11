package com.grupo.controlze.ui.admin

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grupo.controlze.data.repository.FaseRepository
import com.grupo.controlze.logic.Fase
import kotlinx.coroutines.launch

class AdminViewModel(private val repository: FaseRepository) : ViewModel() {
    var uiState by mutableStateOf(AdminUiState())
        private set

    private var todas: List<Fase> = emptyList()

    init {
        viewModelScope.launch {
            repository.fases.collect { fases ->
                todas = fases
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

    private fun atualizarLista() {
        uiState = uiState.copy(itens = itensDoPainel(todas, uiState.filtro))
    }
}
