package com.grupo.controlze.ui.game

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.grupo.controlze.logic.TipoFase
import com.grupo.controlze.ui.game.tipos.Esperar
import com.grupo.controlze.ui.game.tipos.MultiplaEscolha
import com.grupo.controlze.ui.game.tipos.NumeroGigante
import com.grupo.controlze.ui.game.tipos.TocarPalavra

@Composable
fun GameScreen(
    vm: GameViewModel,
    nomeJogador: String,
    mostrarErros: Boolean,
    onVerRanking: () -> Unit,
    onSair: () -> Unit
) {
    val estado = vm.uiState
    val fase = estado.faseAtual

    Column(Modifier.fillMaxSize().padding(16.dp)) {
        estado.mensagem?.let { mensagem ->
            Text(mensagem, color = MaterialTheme.colorScheme.error)
            Spacer(Modifier.height(8.dp))
        }
        if (estado.carregando) {
            Text("Carregando...")
        } else if (estado.venceu) {
            TelaVitoria(
                segundos = estado.tempoFinalSegundos,
                erros = estado.erros,
                onJogarDeNovo = { vm.iniciar(nomeJogador) },
                onVerRanking = onVerRanking,
                onSair = onSair
            )
        } else if (!estado.emAndamento || fase == null) {
            TelaAntesDeComecar(
                nome = nomeJogador,
                totalFases = estado.fasesDisponiveis.size,
                onComecar = { vm.iniciar(nomeJogador) },
                onCarregarOriginais = { vm.carregarOriginais() },
                onSair = onSair
            )
        } else {
            val rolagem = if (fase.tipo == TipoFase.ESPERAR) Modifier
            else Modifier.verticalScroll(rememberScrollState())

            Column(Modifier.fillMaxWidth().weight(1f).then(rolagem)) {
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(
                        "Fase ${estado.indiceAtual + 1} de ${estado.partida.size}",
                        style = MaterialTheme.typography.titleMedium
                    )
                    if (mostrarErros) Text("Erros: ${estado.erros}")
                }
                Spacer(Modifier.height(24.dp))
                when (fase.tipo) {
                    TipoFase.MULTIPLA_ESCOLHA -> MultiplaEscolha(fase) { vm.escolheu(it) }
                    TipoFase.TOCAR_PALAVRA -> TocarPalavra(
                        fase = fase,
                        onTocarPalavra = vm::tocouPalavra,
                        onEscolher = vm::escolheu
                    )
                    TipoFase.NUMERO_GIGANTE -> NumeroGigante(fase, vm::escolheu)
                    TipoFase.ESPERAR -> Esperar(
                        fase = fase,
                        segundos = estado.segundosEspera,
                        onTocar = vm::tocouDuranteEspera
                    )
                }
            }
        }
    }
}