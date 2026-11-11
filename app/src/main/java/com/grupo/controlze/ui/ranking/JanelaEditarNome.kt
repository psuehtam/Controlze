package com.grupo.controlze.ui.ranking

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable

@Composable
fun JanelaEditarNome(
    nome: String,
    onNomeChange: (String) -> Unit,
    onSalvar: () -> Unit,
    onCancelar: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onCancelar,
        title = { Text("Editar nome") },
        text = {
            OutlinedTextField(
                value = nome,
                onValueChange = onNomeChange,
                label = { Text("Nome do jogador") },
                singleLine = true
            )
        },
        confirmButton = { TextButton(onClick = onSalvar) { Text("Salvar") } },
        dismissButton = { TextButton(onClick = onCancelar) { Text("Cancelar") } }
    )
}
