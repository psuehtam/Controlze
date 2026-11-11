package com.grupo.controlze.logic

fun escolhaCerta(fase: Fase, texto: String): Boolean = texto == fase.resposta

fun palavraCerta(fase: Fase, palavra: String): Boolean {
    val alvo = fase.alvo ?: return false
    return normalizar(palavra) == normalizar(alvo)
}

fun normalizar(texto: String): String =
    texto.lowercase().filter { it.isLetterOrDigit() }
