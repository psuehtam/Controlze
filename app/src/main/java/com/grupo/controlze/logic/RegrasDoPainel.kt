package com.grupo.controlze.logic

fun ordemParaNovaFase(fases: List<Fase>): Double {
    val faseFinal = FASES_ORIGINAIS.lastOrNull() ?: return 1.0
    val anteriorOriginal = FASES_ORIGINAIS
        .filter { it.ordem < faseFinal.ordem }
        .maxByOrNull { it.ordem }
    val anterior = (fases.filter { it.ordem < faseFinal.ordem } + listOfNotNull(anteriorOriginal))
        .maxByOrNull { it.ordem }

    return anterior?.let { (it.ordem + faseFinal.ordem) / 2.0 }
        ?: faseFinal.ordem - 1.0
}

fun ordemAoMover(fases: List<Fase>, indice: Int, paraCima: Boolean): Double? {
    val lista = fases.sortedBy { it.ordem }
    val fase = lista.getOrNull(indice) ?: return null
    if (fase.original || fase.id.startsWith("original-")) return null

    val destino = if (paraCima) indice - 1 else indice + 1
    if (destino < 0) return null

    val idFinal = FASES_ORIGINAIS.lastOrNull()?.id
    val indiceFinal = lista.indexOfFirst { it.id == idFinal }
    val limiteFinal = if (indiceFinal >= 0) indiceFinal else lista.lastIndex
    if (!paraCima && destino >= limiteFinal) return null

    return if (paraCima) {
        val depois = lista[destino].ordem
        if (destino == 0) depois - 1.0
        else (lista[destino - 1].ordem + depois) / 2.0
    } else {
        val antes = lista[destino].ordem
        val depois = lista.getOrNull(destino + 1)?.ordem ?: return null
        (antes + depois) / 2.0
    }
}
