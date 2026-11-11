package com.grupo.controlze.ui.sessao

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SessaoViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(SessaoUiState())
    val uiState: StateFlow<SessaoUiState> = _uiState.asStateFlow()

    fun definirNome(nome: String) {
        _uiState.update { it.copy(nomeJogador = nome) }
    }
}
