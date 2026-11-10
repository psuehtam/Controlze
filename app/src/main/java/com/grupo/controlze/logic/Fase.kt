package com.grupo.controlze.logic

data class Fase(
    val id: String,
    val ordem: Double,
    val categoria: String,
    val tipo: TipoFase,
    val pergunta: String,
    val codigo: String? = null,
    val alternativas: List<String> = emptyList(),
    val resposta: String = "",
    val alvo: String? = null,
    val original: Boolean = false
)
