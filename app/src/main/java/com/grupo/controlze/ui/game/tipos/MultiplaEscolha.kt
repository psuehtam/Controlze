package com.grupo.controlze.ui.game.tipos

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import com.grupo.controlze.logic.Fase

@Composable
fun MultiplaEscolha(fase: Fase, onEscolher: (String) -> Unit) {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text(fase.pergunta, style = MaterialTheme.typography.headlineSmall)
        fase.codigo?.let { codigo ->
            Text(
                text = codigo,
                fontFamily = FontFamily.Monospace,
                modifier = Modifier.fillMaxWidth().background(Color.LightGray).padding(12.dp)
            )
        }
        for (alternativa in fase.alternativas) {
            Button(onClick = { onEscolher(alternativa) }, modifier = Modifier.fillMaxWidth()) {
                Text(alternativa)
            }
        }
    }
}
