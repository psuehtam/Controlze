package com.grupo.controlze.ui.admin

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun PainelScreen(
    vm: AdminViewModel,
    onNovaFase: () -> Unit,
    onEditar: (String) -> Unit,
    onSair: () -> Unit
) {
    val estado = vm.uiState

    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        item {
            Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                Text(
                    "Painel de fases",
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.weight(1f)
                )
                TextButton(onClick = onSair) { Text("Sair") }
            }
        }
        item {
            FiltrosPainel(filtro = estado.filtro, onMudar = vm::mudarFiltro)
        }
        item {
            Button(onClick = onNovaFase, modifier = Modifier.fillMaxWidth()) {
                Text("+ Nova fase")
            }
        }
        if (estado.itens.isEmpty() && estado.filtro == FiltroPainel.TODAS) {
            item {
                Button(onClick = vm::carregarOriginais) { Text("Carregar fases originais") }
            }
        }
        estado.mensagem?.let { mensagem ->
            item {
                Text(mensagem, color = MaterialTheme.colorScheme.primary)
            }
        }
        if (estado.itens.isEmpty() && estado.filtro != FiltroPainel.TODAS) {
            item {
                Text("Nenhuma fase encontrada neste filtro.")
            }
        } else {
            items(estado.itens, key = { item -> item.fase.id }) { item ->
                LinhaFase(
                    item = item,
                    mostrarSetas = estado.filtro == FiltroPainel.TODAS,
                    onSubir = { vm.mover(item.fase, paraCima = true) },
                    onDescer = { vm.mover(item.fase, paraCima = false) },
                    onEditar = { onEditar(item.fase.id) },
                    onExcluir = { vm.pedirExclusao(item.fase) }
                )
            }
        }
    }

    estado.confirmarExclusao?.let { fase ->
        JanelaExcluirFase(
            pergunta = fase.pergunta,
            onConfirmar = vm::excluirConfirmada,
            onCancelar = { vm.pedirExclusao(null) }
        )
    }
}