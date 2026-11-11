package com.grupo.controlze.ui.config

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ConfigScreen(
    nome: String,
    mostrarErros: Boolean,
    onNomeChange: (String) -> Unit,
    onMostrarErrosChange: (Boolean) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Configurações", style = MaterialTheme.typography.headlineMedium)
        OutlinedTextField(
            value = nome,
            onValueChange = onNomeChange,
            label = { Text("Nome do jogador") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = mostrarErros,
                onCheckedChange = onMostrarErrosChange
            )
            Text("Mostrar erros durante o jogo")
        }
        Text("Controlze · versão 1.0", style = MaterialTheme.typography.bodySmall)
    }
}
