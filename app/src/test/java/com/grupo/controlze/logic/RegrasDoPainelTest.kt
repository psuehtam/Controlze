package com.grupo.controlze.logic

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

class RegrasDoPainelTest {
    @Test
    fun faseNovaEntraAntesDaFaseFinal() {
        assertEquals(9.5, ordemParaNovaFase(emptyList()), 0.0)
        assertEquals(9.5, ordemParaNovaFase(FASES_ORIGINAIS), 0.0)
    }

    @Test
    fun proximaFaseCriadaPermaneceAntesDaFinal() {
        val criada = Fase(
            id = "criada-1",
            ordem = 9.5,
            categoria = "Criada",
            tipo = TipoFase.MULTIPLA_ESCOLHA,
            pergunta = "Pergunta",
            alternativas = listOf("A", "B", "C", "D"),
            resposta = "A"
        )

        val ordem = ordemParaNovaFase(FASES_ORIGINAIS + criada)
        assertEquals(9.75, ordem, 0.0)
        assertTrue(ordem < FASES_ORIGINAIS.last().ordem)
    }

    @Test
    fun criadaPodePassarPorOriginalMasNaoPelaFaseFinal() {
        val criada = Fase(
            id = "criada-1",
            ordem = 9.5,
            categoria = "Criada",
            tipo = TipoFase.MULTIPLA_ESCOLHA,
            pergunta = "Pergunta",
            alternativas = listOf("A", "B", "C", "D"),
            resposta = "A"
        )
        val fases = FASES_ORIGINAIS + criada

        assertEquals(8.5, ordemAoMover(fases, indice = 9, paraCima = true)!!, 0.0)
        assertNull(ordemAoMover(fases, indice = 9, paraCima = false))
        assertNull(ordemAoMover(fases, indice = 0, paraCima = true))
    }
}
