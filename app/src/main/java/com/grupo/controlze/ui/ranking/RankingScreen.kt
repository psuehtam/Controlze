package com.grupo.controlze.ui.ranking

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun RankingScreen(vm: RankingViewModel, podeGerenciar: Boolean) {
    val estado = vm.uiState

    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        item {
            Text("Ranking", style = MaterialTheme.typography.headlineMedium)
        }
        item {
            OutlinedTextField(
                value = estado.busca,
                onValueChange = vm::buscar,
                label = { Text("Buscar por nome") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
        }
        item {
            Text("Ordenar por", style = MaterialTheme.typography.labelLarge)
        }
        item {
            LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                items(OrdenacaoRanking.entries) { ordenacao ->
                    FilterChip(
                        selected = estado.ordenacao == ordenacao,
                        onClick = { vm.selecionarOrdenacao(ordenacao) },
                        label = { Text(ordenacao.rotulo) }
                    )
                }
            }
        }
        estado.mensagem?.let { mensagem ->
            item {
                Text(mensagem, color = MaterialTheme.colorScheme.error)
            }
        }

        if (estado.itens.isEmpty()) {
            item {
                Text(if (estado.busca.isBlank()) "Ninguém venceu ainda." else "Nenhum resultado encontrado.")
            }
        } else {
            itemsIndexed(estado.itens, key = { _, item -> item.id }) { indice, item ->
                LinhaRanking(
                    posicao = indice + 1,
                    item = item,
                    podeGerenciar = podeGerenciar,
                    onEditar = { vm.abrirEdicao(item) },
                    onExcluir = { vm.pedirExclusao(item) }
                )
            }
            if (podeGerenciar) {
                item {
                    TextButton(onClick = { vm.pedirApagarTudo(true) }) {
                        Text("Apagar todo o ranking")
                    }
                }
            }
        }
    }

    if (podeGerenciar) {
        estado.editando?.let {
            JanelaEditarNome(
                nome = estado.nomeEditado,
                onNomeChange = vm::mudarNomeEditado,
                onSalvar = vm::salvarNome,
                onCancelar = vm::fecharEdicao
            )
        }

        estado.confirmarExclusao?.let { item ->
            AlertDialog(
                onDismissRequest = { vm.pedirExclusao(null) },
                title = { Text("Excluir ${item.nomeJogador}?") },
                text = { Text("Esse resultado será removido do ranking.") },
                confirmButton = {
                    TextButton(onClick = vm::excluirConfirmado) { Text("Excluir") }
                },
                dismissButton = {
                    TextButton(onClick = { vm.pedirExclusao(null) }) { Text("Cancelar") }
                }
            )
        }

        if (estado.confirmarApagarTudo) {
            AlertDialog(
                onDismissRequest = { vm.pedirApagarTudo(false) },
                title = { Text("Apagar todo o ranking?") },
                text = { Text("Todos os resultados serão removidos.") },
                confirmButton = {
                    TextButton(onClick = vm::apagarTudo) { Text("Apagar") }
                },
                dismissButton = {
                    TextButton(onClick = { vm.pedirApagarTudo(false) }) { Text("Cancelar") }
                }
            )
        }
    }
}
