package com.grupo.controlze.data.local

import com.grupo.controlze.logic.Fase
import com.grupo.controlze.logic.TipoFase

fun Fase.paraEntity(): FaseEntity = FaseEntity(
    id = id,
    ordem = ordem,
    categoria = categoria,
    tipo = tipo.name,
    pergunta = pergunta,
    codigo = codigo,
    alternativa1 = alternativas.getOrElse(0) { "" },
    alternativa2 = alternativas.getOrElse(1) { "" },
    alternativa3 = alternativas.getOrElse(2) { "" },
    alternativa4 = alternativas.getOrElse(3) { "" },
    resposta = resposta,
    alvo = alvo,
    original = original
)

fun FaseEntity.paraFase(): Fase = Fase(
    id = id,
    ordem = ordem,
    categoria = categoria,
    tipo = TipoFase.valueOf(tipo),
    pergunta = pergunta,
    codigo = codigo,
    alternativas = listOf(alternativa1, alternativa2, alternativa3, alternativa4)
        .filter { it.isNotEmpty() },
    resposta = resposta,
    alvo = alvo,
    original = original
)
