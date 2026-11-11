package com.grupo.controlze.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.grupo.controlze.ui.inicio.InicioScreen
import com.grupo.controlze.ui.sessao.SessaoViewModel

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val sessaoVm: SessaoViewModel = viewModel()
    val sessao by sessaoVm.uiState.collectAsState()
    val entrada by navController.currentBackStackEntryAsState()
    val rotaAtual = entrada?.destination?.route

    Scaffold(
        bottomBar = {
            if (rotaAtual != null && rotaAtual != Rotas.INICIO) {
                BottomBar(navController, rotaAtual)
            }
        }
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = Rotas.INICIO,
            modifier = Modifier.padding(padding)
        ) {
            composable(Rotas.INICIO) {
                InicioScreen(
                    nome = sessao.nomeJogador,
                    onNomeChange = sessaoVm::definirNome,
                    onJogar = { navController.navigate(Rotas.JOGO) }
                )
            }
            composable(Rotas.JOGO) { TelaProvisoria("Jogo") }
            composable(Rotas.RANKING) { TelaProvisoria("Ranking") }
            composable(Rotas.CONFIG) { TelaProvisoria("Configurações") }
        }
    }
}
