package com.grupo.controlze.data.repository

import com.grupo.controlze.data.local.RankingDao
import com.grupo.controlze.data.local.RankingEntity
import kotlinx.coroutines.flow.Flow

class RankingRepository(private val dao: RankingDao) {
    val ranking: Flow<List<RankingEntity>> = dao.observarTodos()

    suspend fun inserir(item: RankingEntity): String? {
        return try {
            dao.inserir(item)
            null
        } catch (e: Exception) {
            "Erro ao salvar no ranking: ${e.message}"
        }
    }
}
