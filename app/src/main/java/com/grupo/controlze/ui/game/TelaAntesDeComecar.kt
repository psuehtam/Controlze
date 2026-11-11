package com.grupo.controlze.ui.game

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TelaAntesDeComecar(
    nome: String,
    totalFases: Int,
    onComecar: () -> Unit,
    onCarregarOriginais: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (totalFases == 0) {
            Text("Nenhuma fase carregada ainda.")
            Spacer(Modifier.height(16.dp))
            Button(onClick = onCarregarOriginais) { Text("Carregar fases originais") }
        } else {
            Text("Pronto, $nome?", style = MaterialTheme.typography.headlineSmall)
            Text("São $totalFases fases. Errou, volta para a fase 1.")
            Spacer(Modifier.height(16.dp))
            Button(onClick = onComecar) { Text("Começar") }
        }
    }
}
