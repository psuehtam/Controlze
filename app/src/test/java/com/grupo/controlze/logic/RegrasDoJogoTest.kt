package com.grupo.controlze.logic

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class RegrasDoJogoTest {
    @Test
    fun respostaCertaAcerta() {
        assertTrue(escolhaCerta(FASES_ORIGINAIS[0], "Machoke"))
    }

    @Test
    fun respostaErradaErra() {
        assertFalse(escolhaCerta(FASES_ORIGINAIS[0], "Machop"))
    }

    @Test
    fun botaoDaFaseFinalErra() {
        assertFalse(escolhaCerta(FASES_ORIGINAIS[9], "SAIR"))
    }

    @Test
    fun palavraCertaIgnoraMaiusculasEPontuacao() {
        assertEquals("venceu", normalizar("Venceu!"))
        assertTrue(palavraCerta(FASES_ORIGINAIS[9], "venceu!"))
        assertFalse(palavraCerta(FASES_ORIGINAIS[9], "SAIR"))
    }

    @Test
    fun pegadinhasMantemAlvosCorretos() {
        assertTrue(palavraCerta(FASES_ORIGINAIS[2], "certa"))
        assertTrue(escolhaCerta(FASES_ORIGINAIS[6], "3"))
    }

    @Test
    fun apenasNomeCorretoDoProfessorAcerta() {
        val fase = FASES_ORIGINAIS[3]
        assertEquals(4, fase.alternativas.distinct().size)
        assertTrue(escolhaCerta(fase, NOME_PROFESSOR))
        fase.alternativas.filter { it != NOME_PROFESSOR }.forEach {
            assertFalse(escolhaCerta(fase, it))
        }
    }

    @Test
    fun saoDezFasesSemErro() {
        assertEquals(10, FASES_ORIGINAIS.size)
        assertEquals((1..10).map { "original-" + it.toString().padStart(2, '0') },
            FASES_ORIGINAIS.map { it.id })
        assertTrue(FASES_ORIGINAIS.all { it.original })
        assertTrue(problemasDasOriginais().isEmpty())
    }
}
