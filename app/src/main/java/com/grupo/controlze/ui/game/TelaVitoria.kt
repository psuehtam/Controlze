package com.grupo.controlze.ui.game

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun TelaVitoria(
    segundos: Long,
    erros: Int,
    onJogarDeNovo: () -> Unit,
    onVerRanking: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Você venceu!", style = MaterialTheme.typography.headlineMedium)
        Text("Tempo: ${segundos / 60} min ${segundos % 60} s")
        Text("Erros: $erros")
        Spacer(Modifier.height(16.dp))
        Button(onClick = onJogarDeNovo) { Text("Jogar de novo") }
        OutlinedButton(onClick = onVerRanking) { Text("Ver ranking") }
    }
}

@Preview(showBackground = true)
@Composable
fun TelaVitoriaPreview() {
    TelaVitoria(segundos = 95, erros = 3, onJogarDeNovo = {}, onVerRanking = {})
}
