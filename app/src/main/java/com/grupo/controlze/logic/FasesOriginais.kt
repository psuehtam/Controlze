package com.grupo.controlze.logic

private fun original(
    numero: Int,
    categoria: String,
    tipo: TipoFase,
    pergunta: String,
    codigo: String? = null,
    alternativas: List<String> = emptyList(),
    resposta: String = "",
    alvo: String? = null
) = Fase(
    id = "original-" + numero.toString().padStart(2, '0'),
    ordem = numero.toDouble(),
    categoria = categoria,
    tipo = tipo,
    pergunta = pergunta,
    codigo = codigo,
    alternativas = alternativas,
    resposta = resposta,
    alvo = alvo,
    original = true
)

private val ME = TipoFase.MULTIPLA_ESCOLHA

const val NOME_PROFESSOR = "Andre Luis Jeller Selleti"

val FASES_ORIGINAIS: List<Fase> = listOf(
    original(
        1, "Pokémon", ME, "Qual o Pokémon nº 67 da Pokédex?",
        alternativas = listOf("Machop", "Machoke", "Machamp", "Primeape"), resposta = "Machoke"
    ),
    original(
        2, "Kotlin", ME, "Qual a saída do código?", codigo = "val x = 5\nprintln(x * 2)",
        alternativas = listOf("10", "25", "5", "Erro"), resposta = "10"
    ),
    original(
        3, "Pegadinha", TipoFase.TOCAR_PALAVRA, "Clique na resposta certa",
        alternativas = listOf("A", "B", "C", "D"), alvo = "certa"
    ),
    original(
        4, "Professor", ME, "Qual o nome completo correto do professor?",
        alternativas = listOf(
            "Andre Luis Jeller Seletti", "Andre Luiz Jeller Selleti",
            NOME_PROFESSOR, "Andre Luis Geller Selleti"
        ), resposta = NOME_PROFESSOR
    ),
    original(5, "Pegadinha", TipoFase.ESPERAR, "Não toque em nada por 5 segundos"),
    original(
        6, "Kotlin", ME, "Qual a saída do código?",
        codigo = "val a = \"10\"\nval b = 5\nprintln(a + b)",
        alternativas = listOf("15", "105", "Erro", "10 5"), resposta = "105"
    ),
    original(
        7, "Pegadinha", TipoFase.NUMERO_GIGANTE, "Toque no MAIOR número",
        alternativas = listOf("9", "15", "3", "7"), resposta = "3", alvo = "3"
    ),
    original(
        8, "Kotlin", ME, "Qual a saída do código?", codigo = "println(1 / 2)",
        alternativas = listOf("0.5", "0", "1", "Erro"), resposta = "0"
    ),
    original(
        9, "Lógica", ME,
        "Um médico te dá 3 comprimidos, um a cada meia hora. Quanto tempo eles duram?",
        alternativas = listOf("1h30", "1 hora", "2 horas", "30 min"), resposta = "1 hora"
    ),
    original(
        10, "Pegadinha", TipoFase.TOCAR_PALAVRA, "Parabéns, você venceu! Toque em SAIR",
        alternativas = listOf("SAIR"), alvo = "venceu"
    )
)

fun problemasDasOriginais(): List<String> {
    val problemas = mutableListOf<String>()
    if (FASES_ORIGINAIS.size != 10) problemas.add("Precisam ser 10 fases.")
    for (fase in FASES_ORIGINAIS) {
        val temRespostaEmBotao = fase.tipo == TipoFase.MULTIPLA_ESCOLHA ||
                fase.tipo == TipoFase.NUMERO_GIGANTE
        if (temRespostaEmBotao && fase.resposta !in fase.alternativas)
            problemas.add("${fase.id}: a resposta não está nas alternativas.")
        if (fase.tipo == TipoFase.TOCAR_PALAVRA && fase.alvo == null)
            problemas.add("${fase.id}: falta a palavra-alvo.")
    }
    return problemas
}
