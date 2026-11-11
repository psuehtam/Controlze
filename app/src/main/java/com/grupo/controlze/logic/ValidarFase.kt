package com.grupo.controlze.logic

fun erroNaFaseCriada(
    pergunta: String,
    alternativas: List<String>,
    indiceCerto: Int
): String? {
    if (pergunta.isBlank()) return "Escreva a pergunta."
    if (alternativas.size != 4 || alternativas.any { it.trim().isBlank() }) {
        return "Preencha as quatro alternativas."
    }
    val limpas = alternativas.map { it.trim() }
    if (limpas.map { it.lowercase() }.distinct().size != limpas.size) {
        return "As quatro alternativas precisam ser diferentes."
    }
    if (indiceCerto !in limpas.indices) return "Marque qual alternativa é a certa."
    return null
}
