package com.grupo.controlze.ui.admin

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun LinhaFase(item: ItemPainel) {
    Card(Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(Modifier.weight(1f)) {
                Text(
                    "Fase ${item.numero} · ${item.fase.categoria}",
                    style = MaterialTheme.typography.titleSmall
                )
                Text(item.fase.pergunta, maxLines = 2)
            }
            if (item.fase.original) {
                Icon(Icons.Default.Lock, contentDescription = "Fase original bloqueada")
            } else {
                Text("Criada", style = MaterialTheme.typography.labelMedium)
            }
        }
    }
}
