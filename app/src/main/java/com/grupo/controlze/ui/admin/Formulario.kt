package com.grupo.controlze.ui.admin

data class Formulario(
    val id: String? = null,
    val ordem: Double? = null,
    val pergunta: String = "",
    val alternativas: List<String> = listOf("", "", "", ""),
    val indiceCerto: Int = -1
)
