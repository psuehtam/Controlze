package com.grupo.controlze.ui.ranking

import com.grupo.controlze.data.local.RankingEntity
import org.junit.Assert.assertEquals
import org.junit.Test

class RankingOrdenacaoTest {
    private val itens = listOf(
        RankingEntity(id = 1, nomeJogador = "Bia", totalFases = 10, erros = 1, tempoSegundos = 90),
        RankingEntity(id = 2, nomeJogador = "Ana", totalFases = 10, erros = 0, tempoSegundos = 110),
        RankingEntity(id = 3, nomeJogador = "Caio", totalFases = 10, erros = 0, tempoSegundos = 80)
    )

    @Test
    fun filtraPorNomeSemDiferenciarMaiusculas() {
        val resultado = filtrarEOrdenarRanking(itens, "AN", OrdenacaoRanking.POSICAO)

        assertEquals(listOf(2), resultado.map { it.id })
    }

    @Test
    fun ordenaPorPosicaoErrosTempoNomeETempo() {
        assertEquals(listOf(3, 2, 1),
            filtrarEOrdenarRanking(itens, "", OrdenacaoRanking.POSICAO).map { it.id })
        assertEquals(listOf(2, 1, 3),
            filtrarEOrdenarRanking(itens, "", OrdenacaoRanking.NOME).map { it.id })
        assertEquals(listOf(3, 2, 1),
            filtrarEOrdenarRanking(itens, "", OrdenacaoRanking.ERROS).map { it.id })
        assertEquals(listOf(3, 1, 2),
            filtrarEOrdenarRanking(itens, "", OrdenacaoRanking.TEMPO).map { it.id })
    }
}
