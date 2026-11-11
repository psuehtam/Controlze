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
