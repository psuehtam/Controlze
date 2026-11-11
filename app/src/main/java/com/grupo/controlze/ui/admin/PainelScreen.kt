package com.grupo.controlze.ui.admin

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Text(
                "Painel de fases",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.weight(1f)
            )
            TextButton(onClick = onSair) { Text("Sair") }
        }
        FiltrosPainel(filtro = estado.filtro, onMudar = vm::mudarFiltro)
        Button(onClick = onNovaFase, modifier = Modifier.fillMaxWidth()) {
            Text("+ Nova fase")
        }
        if (estado.itens.isEmpty() && estado.filtro == FiltroPainel.TODAS) {
            Button(onClick = vm::carregarOriginais) { Text("Carregar fases originais") }
        }
        estado.mensagem?.let { mensagem ->
            Text(mensagem, color = MaterialTheme.colorScheme.primary)
        }
        if (estado.itens.isEmpty() && estado.filtro != FiltroPainel.TODAS) {
            Text("Nenhuma fase encontrada neste filtro.")
        } else {
            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(estado.itens, key = { item -> item.fase.id }) { item ->
                    LinhaFase(item = item, onEditar = { onEditar(item.fase.id) })
                }
            }
        }
    }
}
