package com.grupo.controlze.ui.game.tipos

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.grupo.controlze.logic.Fase

@Composable
fun Esperar(fase: Fase, segundos: Int, onTocar: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize().clickable(onClick = onTocar),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(fase.pergunta, style = MaterialTheme.typography.headlineSmall)
        Text("$segundos", style = MaterialTheme.typography.displayLarge)
    }
}
