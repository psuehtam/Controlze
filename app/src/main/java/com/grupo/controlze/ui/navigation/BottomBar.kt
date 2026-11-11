package com.grupo.controlze.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

@Composable
fun BottomBar(navController: NavHostController, rotaAtual: String?, isAdmin: Boolean) {
    NavigationBar {
        NavigationBarItem(
            selected = rotaAtual == Rotas.JOGO,
            onClick = { irPara(navController, Rotas.JOGO) },
            icon = { Icon(Icons.Default.PlayArrow, contentDescription = null) },
            label = { Text("Jogar") }
        )
        NavigationBarItem(
            selected = rotaAtual == Rotas.RANKING,
            onClick = { irPara(navController, Rotas.RANKING) },
            icon = { Icon(Icons.Default.Star, contentDescription = null) },
            label = { Text("Ranking") }
        )
        NavigationBarItem(
            selected = rotaAtual == Rotas.CONFIG,
            onClick = { irPara(navController, Rotas.CONFIG) },
            icon = { Icon(Icons.Default.Settings, contentDescription = null) },
            label = { Text("Config") }
        )
        if (isAdmin) {
            NavigationBarItem(
                selected = rotaAtual == Rotas.PAINEL,
                onClick = { irPara(navController, Rotas.PAINEL) },
                icon = { Icon(Icons.Default.Edit, contentDescription = null) },
                label = { Text("Painel") }
            )
        }
    }
}

private fun irPara(navController: NavHostController, rota: String) {
    navController.navigate(rota) {
        popUpTo(Rotas.INICIO)
        launchSingleTop = true
    }
}
