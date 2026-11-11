package com.grupo.controlze.ui.admin

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

@Composable
fun FiltrosPainel(filtro: FiltroPainel, onMudar: (FiltroPainel) -> Unit) {
    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        FiltroPainel.entries.forEach { opcao ->
            FilterChip(
                selected = filtro == opcao,
                onClick = { onMudar(opcao) },
                label = { Text(opcao.rotulo) }
            )
        }
    }
}
