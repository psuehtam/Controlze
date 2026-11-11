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

    suspend fun atualizar(item: RankingEntity): String? {
        return try {
            dao.atualizar(item)
            null
        } catch (e: Exception) {
            "Erro ao editar o ranking: ${e.message}"
        }
    }

    suspend fun excluir(item: RankingEntity): String? {
        return try {
            dao.excluir(item)
            null
        } catch (e: Exception) {
            "Erro ao excluir do ranking: ${e.message}"
        }
    }

    suspend fun excluirTodos(): String? {
        return try {
            dao.excluirTodos()
            null
        } catch (e: Exception) {
            "Erro ao apagar o ranking: ${e.message}"
        }
    }
}
