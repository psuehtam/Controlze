package com.grupo.controlze.ui.ranking

import com.grupo.controlze.data.local.RankingEntity

enum class OrdenacaoRanking(val rotulo: String) {
    POSICAO("Posição"),
    NOME("Nome"),
    ERROS("Erros"),
    TEMPO("Tempo")
}

data class RankingUiState(
    val itens: List<RankingEntity> = emptyList(),
    val busca: String = "",
    val ordenacao: OrdenacaoRanking = OrdenacaoRanking.POSICAO,
    val editando: RankingEntity? = null,
    val nomeEditado: String = "",
    val confirmarExclusao: RankingEntity? = null,
    val confirmarApagarTudo: Boolean = false,
    val mensagem: String? = null
)

fun filtrarEOrdenarRanking(
    itens: List<RankingEntity>,
    busca: String,
    ordenacao: OrdenacaoRanking
): List<RankingEntity> {
    val filtrados = itens.filter { item ->
        item.nomeJogador.contains(busca.trim(), ignoreCase = true)
    }
    val comparador = when (ordenacao) {
        OrdenacaoRanking.POSICAO,
        OrdenacaoRanking.ERROS -> compareBy<RankingEntity> { it.erros }
            .thenBy { it.tempoSegundos }
            .thenBy { it.id }
        OrdenacaoRanking.NOME -> compareBy<RankingEntity> { it.nomeJogador.lowercase() }
            .thenBy { it.erros }
            .thenBy { it.tempoSegundos }
            .thenBy { it.id }
        OrdenacaoRanking.TEMPO -> compareBy<RankingEntity> { it.tempoSegundos }
            .thenBy { it.erros }
            .thenBy { it.id }
    }
    return filtrados.sortedWith(comparador)
}
