package com.grupo.controlze.ui.sessao

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Test

class SessaoViewModelTest {
    @Test
    fun atualizaNomeEOpcaoDeMostrarErros() {
        val viewModel = SessaoViewModel()

        viewModel.definirNome("Gabriel")
        viewModel.mudarMostrarErros(false)

        assertEquals("Gabriel", viewModel.uiState.nomeJogador)
        assertFalse(viewModel.uiState.mostrarErros)
    }
}
