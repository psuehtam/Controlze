package com.grupo.controlze.logic

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class ValidarFaseTest {
    private val alternativas = listOf("A", "B", "C", "D")

    @Test
    fun exigePerguntaQuatroAlternativasDiferentesERespostaCerta() {
        assertEquals(
            "Escreva a pergunta.",
            erroNaFaseCriada("  ", alternativas, 0)
        )
        assertEquals(
            "Preencha as quatro alternativas.",
            erroNaFaseCriada("Pergunta", listOf("A", "B", "C"), 0)
        )
        assertEquals(
            "As quatro alternativas precisam ser diferentes.",
            erroNaFaseCriada("Pergunta", listOf("A", "a", "C", "D"), 0)
        )
        assertEquals(
            "Marque qual alternativa é a certa.",
            erroNaFaseCriada("Pergunta", alternativas, -1)
        )
    }

    @Test
    fun aceitaFormularioCompleto() {
        assertNull(erroNaFaseCriada("Pergunta", alternativas, 2))
    }
}
