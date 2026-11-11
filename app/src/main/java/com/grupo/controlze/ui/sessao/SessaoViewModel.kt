package com.grupo.controlze.ui.sessao

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class SessaoViewModel : ViewModel() {
    var uiState by mutableStateOf(SessaoUiState())
        private set

    fun definirNome(nome: String) {
        uiState = uiState.copy(nomeJogador = nome)
    }

    fun entrarNoPainel() {
        uiState = uiState.copy(isAdmin = true)
    }

    fun sairDoPainel() {
        uiState = uiState.copy(isAdmin = false)
    }
}
