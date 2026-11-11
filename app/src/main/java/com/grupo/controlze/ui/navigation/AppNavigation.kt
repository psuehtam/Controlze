package com.grupo.controlze.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.firebase.firestore.FirebaseFirestore
import com.grupo.controlze.data.local.AppDatabase
import com.grupo.controlze.data.remote.FaseRemoteDataSource
import com.grupo.controlze.data.repository.FaseRepository
import com.grupo.controlze.data.repository.RankingRepository
import com.grupo.controlze.ui.AppViewModelFactory
import com.grupo.controlze.ui.game.GameScreen
import com.grupo.controlze.ui.game.GameViewModel
import com.grupo.controlze.ui.inicio.InicioScreen
import com.grupo.controlze.ui.ranking.RankingScreen
import com.grupo.controlze.ui.ranking.RankingViewModel
import com.grupo.controlze.ui.sessao.SessaoViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val context = LocalContext.current
    val factory = remember(context) {
        val banco = AppDatabase.getDatabase(context)
        AppViewModelFactory(
            FaseRepository(banco.faseDao(), FaseRemoteDataSource(FirebaseFirestore.getInstance())),
            RankingRepository(banco.rankingDao())
        )
    }
    val sessaoVm: SessaoViewModel = viewModel()
    val gameVm: GameViewModel = viewModel(factory = factory)
    val rankingVm: RankingViewModel = viewModel(factory = factory)
    val sessao = sessaoVm.uiState
    val entrada by navController.currentBackStackEntryAsState()
    val rotaAtual = entrada?.destination?.route

    Scaffold(
        topBar = {
            if (rotaAtual != null && rotaAtual != Rotas.INICIO) {
                TopAppBar(
                    title = { Text("Controlze") },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer
                    )
                )
            }
        },
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
            composable(Rotas.JOGO) {
                GameScreen(
                    vm = gameVm,
                    nomeJogador = sessao.nomeJogador.trim(),
                    onVerRanking = { navController.navigate(Rotas.RANKING) }
                )
            }
            composable(Rotas.RANKING) { RankingScreen(rankingVm) }
            composable(Rotas.CONFIG) { TelaProvisoria("Configurações") }
        }
    }
}
