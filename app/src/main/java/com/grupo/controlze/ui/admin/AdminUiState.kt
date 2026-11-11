package com.grupo.controlze.ui.admin

import com.grupo.controlze.logic.Fase

data class AdminUiState(
    val itens: List<ItemPainel> = emptyList(),
    val filtro: FiltroPainel = FiltroPainel.TODAS,
    val mensagem: String? = null
)

fun itensDoPainel(fases: List<Fase>, filtro: FiltroPainel): List<ItemPainel> =
    fases.sortedBy { it.ordem }
        .mapIndexed { indice, fase -> ItemPainel(numero = indice + 1, fase = fase) }
        .filter { item ->
            when (filtro) {
                FiltroPainel.TODAS -> true
                FiltroPainel.ORIGINAIS -> item.fase.original
                FiltroPainel.CRIADAS -> !item.fase.original
            }
        }
