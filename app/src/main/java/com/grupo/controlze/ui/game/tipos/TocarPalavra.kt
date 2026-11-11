package com.grupo.controlze.ui.game.tipos

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.grupo.controlze.logic.Fase

@Composable
fun TocarPalavra(
    fase: Fase,
    onTocarPalavra: (String) -> Unit,
    onEscolher: (String) -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        fase.pergunta.split(" ").chunked(3).forEach { linha ->
            Row {
                linha.forEach { palavra ->
                    Text(
                        text = "$palavra ",
                        style = MaterialTheme.typography.headlineSmall,
                        modifier = Modifier.clickable { onTocarPalavra(palavra) }
                    )
                }
            }
        }

        fase.alternativas.forEach { alternativa ->
            Button(
                onClick = { onEscolher(alternativa) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(alternativa)
            }
        }
    }
}
