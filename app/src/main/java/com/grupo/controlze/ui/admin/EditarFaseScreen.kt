package com.grupo.controlze.ui.admin

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun EditarFaseScreen(vm: AdminViewModel, id: String, onVoltar: () -> Unit) {
    LaunchedEffect(id) {
        vm.abrirFormulario(id)
    }

    val estado = vm.uiState
    val formulario = estado.formulario

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = when {
                estado.edicaoBloqueada -> "Fase bloqueada"
                formulario.id == null -> "Nova fase"
                else -> "Editar fase"
            },
            style = MaterialTheme.typography.headlineSmall
        )

        if (!estado.edicaoBloqueada) {
            OutlinedTextField(
                value = formulario.pergunta,
                onValueChange = vm::mudarPergunta,
                label = { Text("Pergunta") },
                modifier = Modifier.fillMaxWidth(),
                enabled = !estado.salvando
            )

            Text("Alternativas (marque a certa):")
            for (posicao in 0..3) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = formulario.indiceCerto == posicao,
                        onClick = { vm.marcarCerta(posicao) },
                        enabled = !estado.salvando
                    )
                    OutlinedTextField(
                        value = formulario.alternativas.getOrElse(posicao) { "" },
                        onValueChange = { vm.mudarAlternativa(posicao, it) },
                        label = { Text("Opção ${posicao + 1}") },
                        singleLine = true,
                        modifier = Modifier.weight(1f),
                        enabled = !estado.salvando
                    )
                }
            }
        }

        estado.mensagem?.let { mensagem ->
            Text(mensagem, color = MaterialTheme.colorScheme.error)
        }

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            OutlinedButton(onClick = onVoltar) { Text("Cancelar") }
            if (!estado.edicaoBloqueada) {
                Button(
                    onClick = { vm.salvar(aoTerminar = onVoltar) },
                    enabled = !estado.salvando
                ) {
                    Text(if (estado.salvando) "Salvando..." else "Salvar")
                }
            }
        }
    }
}
