package com.grupo.controlze.data.remote

import com.grupo.controlze.logic.Fase
import com.grupo.controlze.logic.TipoFase

data class FaseDto(
    var ordem: Double = 0.0,
    var categoria: String = "",
    var tipo: String = "MULTIPLA_ESCOLHA",
    var pergunta: String = "",
    var codigo: String? = null,
    var alternativas: List<String> = emptyList(),
    var resposta: String = "",
    var alvo: String? = null,
    var original: Boolean = false
)

fun FaseDto.paraFase(id: String): Fase? {
    val tipoFase = TipoFase.entries.find { it.name == tipo } ?: return null
    return Fase(
    id = id,
    ordem = ordem,
    categoria = categoria,
    tipo = tipoFase,
    pergunta = pergunta,
    codigo = codigo,
    alternativas = alternativas,
    resposta = resposta,
    alvo = alvo,
    original = original
    )
}

fun Fase.paraDto(): FaseDto = FaseDto(
    ordem = ordem,
    categoria = categoria,
    tipo = tipo.name,
    pergunta = pergunta,
    codigo = codigo,
    alternativas = alternativas,
    resposta = resposta,
    alvo = alvo,
    original = original
)
