package com.grupo.controlze.logic

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class RegrasDoJogoTest {
    @Test
    fun respostaCertaAcerta() {
        assertTrue(escolhaCerta(FASES_ORIGINAIS[0], "Pikachu"))
    }

    @Test
    fun respostaErradaErra() {
        assertFalse(escolhaCerta(FASES_ORIGINAIS[0], "Raichu"))
    }

    @Test
    fun botaoDaFase30Erra() {
        assertFalse(escolhaCerta(FASES_ORIGINAIS[29], "SAIR"))
    }

    @Test
    fun apenasNomeCorretoDoProfessorAcerta() {
        val fase = FASES_ORIGINAIS[8]
        assertEquals(4, fase.alternativas.distinct().size)
        assertTrue(escolhaCerta(fase, NOME_PROFESSOR))
        fase.alternativas.filter { it != NOME_PROFESSOR }.forEach {
            assertFalse(escolhaCerta(fase, it))
        }
    }

    @Test
    fun saoTrintaFasesSemErro() {
        assertEquals(30, FASES_ORIGINAIS.size)
        assertEquals((1..30).map { "original-" + it.toString().padStart(2, '0') },
            FASES_ORIGINAIS.map { it.id })
        assertTrue(FASES_ORIGINAIS.all { it.original })
        assertTrue(problemasDasOriginais().isEmpty())
    }
}
