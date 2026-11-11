package com.grupo.controlze.ui.navigation

object Rotas {
    const val INICIO = "inicio"
    const val JOGO = "jogo"
    const val RANKING = "ranking"
    const val CONFIG = "config"
    const val PAINEL = "painel"
    const val NOVA = "nova"
    const val EDITAR = "editar/{id}"

    fun editar(id: String): String = "editar/$id"
}
