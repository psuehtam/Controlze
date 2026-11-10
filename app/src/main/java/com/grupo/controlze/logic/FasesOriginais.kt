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
    original(1, "Pokémon", ME, "Qual o Pokémon nº 25 da Pokédex?",
        alternativas = listOf("Raichu", "Pikachu", "Pichu", "Jigglypuff"), resposta = "Pikachu"),
    original(2, "Geral", ME, "Qual o maior planeta do Sistema Solar?",
        alternativas = listOf("Saturno", "Terra", "Júpiter", "Netuno"), resposta = "Júpiter"),
    original(3, "Kotlin", ME, "Qual a saída do código?", codigo = "val x = 5\nprintln(x * 2)",
        alternativas = listOf("10", "25", "5", "Erro"), resposta = "10"),
    original(4, "Charada", ME, "O que é, o que é: quanto mais se tira, maior fica?",
        alternativas = listOf("Foto", "Buraco", "Cabelo", "Dívida"), resposta = "Buraco"),
    original(5, "Geografia", ME, "Qual a capital da Austrália?",
        alternativas = listOf("Sydney", "Melbourne", "Canberra", "Perth"), resposta = "Canberra"),
    original(6, "Pegadinha", TipoFase.TOCAR_PALAVRA, "Clique na resposta certa",
        alternativas = listOf("A", "B", "C", "D"), alvo = "certa"),
    original(7, "Pokémon", ME, "Qual o Pokémon nº 1 da Pokédex?",
        alternativas = listOf("Pikachu", "Charmander", "Bulbasaur", "Mew"), resposta = "Bulbasaur"),
    original(8, "Ciência", ME, "O que pesa mais, 1 kg de pena ou 1 kg de ferro?",
        alternativas = listOf("Ferro", "Pena", "Pesam igual", "Depende"), resposta = "Pesam igual"),
    original(9, "Professor", ME, "Qual o nome completo correto do professor?",
        alternativas = listOf("Andre Luis Jeller Seletti", "Andre Luiz Jeller Selleti",
            NOME_PROFESSOR, "Andre Luis Geller Selleti"),
        resposta = NOME_PROFESSOR),
    original(10, "Kotlin", ME, "Qual a saída do código?",
        codigo = "val nome = \"Kotlin\"\nprintln(nome.length)",
        alternativas = listOf("5", "6", "7", "Erro"), resposta = "6"),
    original(11, "Pokémon", ME, "Qual o primeiro líder de ginásio de Kanto?",
        alternativas = listOf("Misty", "Brock", "Lt. Surge", "Erika"), resposta = "Brock"),
    original(12, "Pegadinha", TipoFase.ESPERAR, "Não toque em nada por 5 segundos"),
    original(13, "Pokémon", ME, "Qual tipo é super efetivo contra Água?",
        alternativas = listOf("Fogo", "Grama", "Pedra", "Gelo"), resposta = "Grama"),
    original(14, "Kotlin", ME, "Qual a saída do código?",
        codigo = "val lista = listOf(1, 2, 3, 4)\nprintln(lista.filter { it % 2 == 0 })",
        alternativas = listOf("[1, 3]", "[2, 4]", "[2]", "6"), resposta = "[2, 4]"),
    original(15, "Lógica", ME,
        "Um médico te dá 3 comprimidos, um a cada meia hora. Quanto tempo eles duram?",
        alternativas = listOf("1h30", "1 hora", "2 horas", "30 min"), resposta = "1 hora"),
    original(16, "Pokémon", ME, "Qual o nome completo do Professor Carvalho?",
        alternativas = listOf("Gary Carvalho", "Samuel Carvalho", "Ash Carvalho", "Pedro Carvalho"),
        resposta = "Samuel Carvalho"),
    original(17, "Geral", ME, "Qual o nome completo do Pelé?",
        alternativas = listOf("Edson Arantes do Nascimento", "Edson Arantes da Silva",
            "Edison Nascimento Arantes", "Edson Antunes do Nascimento"),
        resposta = "Edson Arantes do Nascimento"),
    original(18, "Pegadinha", TipoFase.NUMERO_GIGANTE, "Toque no MAIOR número",
        alternativas = listOf("9", "15", "3", "7"), resposta = "3", alvo = "3"),
    original(19, "Geografia", ME, "Qual país tem mais fusos horários?",
        alternativas = listOf("Rússia", "EUA", "França", "China"), resposta = "França"),
    original(20, "Pokémon", ME, "Qual o tipo duplo do Gyarados?",
        alternativas = listOf("Água/Dragão", "Água/Voador", "Água/Sombrio", "Água/Gelo"),
        resposta = "Água/Voador"),
    original(21, "Kotlin", ME, "Qual a saída do código?",
        codigo = "val a = \"10\"\nval b = 5\nprintln(a + b)",
        alternativas = listOf("15", "105", "Erro", "10 5"), resposta = "105"),
    original(22, "História", ME, "Qual o nome completo de Tiradentes?",
        alternativas = listOf("Joaquim José da Silva Xavier", "José Joaquim da Silva Xavier",
            "Joaquim José Xavier da Silva", "Joaquim da Silva Tiradentes"),
        resposta = "Joaquim José da Silva Xavier"),
    original(23, "Kotlin", ME, "Qual a saída do código?", codigo = "println(1 / 2)",
        alternativas = listOf("0.5", "0", "1", "Erro"), resposta = "0"),
    original(24, "Pegadinha", TipoFase.BOTAO_FOGE, "Quanto é 5 + 5?",
        alternativas = listOf("55", "0", "100", "10"), resposta = "10"),
    original(25, "Pokémon", ME, "Qual o Pokémon nº 67 da Pokédex?",
        alternativas = listOf("Machop", "Machoke", "Machamp", "Primeape"), resposta = "Machoke"),
    original(26, "Kotlin", ME, "Qual a saída do código?",
        codigo = "val nome: String? = null\nprintln(nome?.length ?: \"vazio\")",
        alternativas = listOf("null", "0", "vazio", "Erro"), resposta = "vazio"),
    original(27, "Astronomia", ME, "Em qual planeta o dia é mais longo que o ano?",
        alternativas = listOf("Mercúrio", "Vênus", "Marte", "Netuno"), resposta = "Vênus"),
    original(28, "Kotlin", ME, "Qual a saída do código?", codigo = "var i = 0\nprintln(i++ + ++i)",
        alternativas = listOf("1", "2", "0", "3"), resposta = "2"),
    original(29, "Pokémon", ME, "Em que nível o Magikarp evolui?",
        alternativas = listOf("15", "25", "20", "30"), resposta = "20"),
    original(30, "Pegadinha", TipoFase.TOCAR_PALAVRA, "Parabéns, você venceu! Toque em SAIR",
        alternativas = listOf("SAIR"), alvo = "venceu")
)

fun problemasDasOriginais(): List<String> {
    val problemas = mutableListOf<String>()
    if (FASES_ORIGINAIS.size != 30) problemas.add("Precisam ser 30 fases.")
    for (fase in FASES_ORIGINAIS) {
        val temRespostaEmBotao = fase.tipo == TipoFase.MULTIPLA_ESCOLHA ||
            fase.tipo == TipoFase.NUMERO_GIGANTE || fase.tipo == TipoFase.BOTAO_FOGE
        if (temRespostaEmBotao && fase.resposta !in fase.alternativas)
            problemas.add("${fase.id}: a resposta não está nas alternativas.")
        if (fase.tipo == TipoFase.TOCAR_PALAVRA && fase.alvo == null)
            problemas.add("${fase.id}: falta a palavra-alvo.")
    }
    return problemas
}
