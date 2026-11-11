package com.grupo.controlze.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.grupo.controlze.data.repository.FaseRepository
import com.grupo.controlze.data.repository.RankingRepository
import com.grupo.controlze.ui.admin.AdminViewModel
import com.grupo.controlze.ui.game.GameViewModel
import com.grupo.controlze.ui.ranking.RankingViewModel

class AppViewModelFactory(
    private val faseRepository: FaseRepository,
    private val rankingRepository: RankingRepository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GameViewModel::class.java)) {
            return GameViewModel(faseRepository, rankingRepository) as T
        }
        if (modelClass.isAssignableFrom(RankingViewModel::class.java)) {
            return RankingViewModel(rankingRepository) as T
        }
        if (modelClass.isAssignableFrom(AdminViewModel::class.java)) {
            return AdminViewModel(faseRepository) as T
        }
        throw IllegalArgumentException("ViewModel desconhecido: ${modelClass.name}")
    }
}
