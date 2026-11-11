package com.grupo.controlze.ui.ranking

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.grupo.controlze.data.local.RankingEntity

@Composable
fun LinhaRanking(
    posicao: Int,
    item: RankingEntity,
    onEditar: () -> Unit,
    onExcluir: () -> Unit
) {
    Card(Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(Modifier.weight(1f)) {
                Text("$posicao. ${item.nomeJogador}", style = MaterialTheme.typography.titleMedium)
                Text(
                    "${item.totalFases} fases · ${item.erros} erros · ${formatarTempo(item.tempoSegundos)}"
                )
            }
            IconButton(onClick = onEditar) {
                Icon(Icons.Default.Edit, contentDescription = "Editar nome")
            }
            IconButton(onClick = onExcluir) {
                Icon(Icons.Default.Delete, contentDescription = "Excluir resultado")
            }
        }
    }
}

fun formatarTempo(segundos: Long): String =
    "${segundos / 60} min ${segundos % 60} s"
