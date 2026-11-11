package com.grupo.controlze.ui.game

import com.grupo.controlze.logic.Fase

data class GameUiState(
    val fasesDisponiveis: List<Fase> = emptyList(),
    val partida: List<Fase> = emptyList(),
    val emAndamento: Boolean = false,
    val indiceAtual: Int = 0,
    val erros: Int = 0,
    val inicioMillis: Long = 0L,
    val venceu: Boolean = false,
    val tempoFinalSegundos: Long = 0L,
    val mensagem: String? = null,
    val carregando: Boolean = true
) {
    val faseAtual: Fase? get() = partida.getOrNull(indiceAtual)
}
