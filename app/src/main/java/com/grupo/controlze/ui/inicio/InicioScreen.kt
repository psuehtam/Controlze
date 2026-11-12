package com.grupo.controlze.ui.inicio

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.grupo.controlze.ui.componentes.ColunaRolavel

@Composable
fun InicioScreen(
    nome: String,
    onNomeChange: (String) -> Unit,
    onJogar: () -> Unit,
    onAbrirPainel: () -> Unit
) {
    ColunaRolavel(
        modifier = Modifier.padding(24.dp)
    )
    {
        Text("Controlze", style = MaterialTheme.typography.displayMedium)
        Text("Errou? Volta pro começo.")
        Spacer(Modifier.height(32.dp))
        OutlinedTextField(
            value = nome,
            onValueChange = onNomeChange,
            label = { Text("Seu nome") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(16.dp))
        Button(
            onClick = onJogar,
            enabled = nome.isNotBlank(),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Jogar")
        }
        Spacer(Modifier.height(8.dp))
        OutlinedButton(onClick = onAbrirPainel, modifier = Modifier.fillMaxWidth()) {
            Text("Painel de fases")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun InicioScreenPreview() {
    InicioScreen(nome = "Ana", onNomeChange = {}, onJogar = {}, onAbrirPainel = {})
}
