package com.grupo.controlze.ui.game.tipos

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.grupo.controlze.logic.Fase

@Composable
fun NumeroGigante(fase: Fase, onEscolher: (String) -> Unit) {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text(fase.pergunta, style = MaterialTheme.typography.headlineSmall)
        fase.alternativas.forEach { alternativa ->
            val tamanho = if (alternativa == fase.alvo) 64.sp else 18.sp
            Button(
                onClick = { onEscolher(alternativa) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(alternativa, fontSize = tamanho)
            }
        }
    }
}
