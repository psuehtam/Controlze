package com.grupo.controlze.data.repository

import com.grupo.controlze.data.local.FaseDao
import com.grupo.controlze.data.local.paraEntity
import com.grupo.controlze.data.local.paraFase
import com.grupo.controlze.data.remote.FaseRemoteDataSource
import com.grupo.controlze.logic.FASES_ORIGINAIS
import com.grupo.controlze.logic.Fase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FaseRepository(
    private val dao: FaseDao,
    private val remote: FaseRemoteDataSource
) {
    val fases: Flow<List<Fase>> = dao.observarTodas().map { lista ->
        lista.map { it.paraFase() }
    }

    suspend fun sincronizar(): String? {
        return try {
            val remotas = remote.buscarTodas()
            if (remotas.isNotEmpty()) {
                dao.salvarTodas(remotas.map { it.paraEntity() })
                val idsRemotos = remotas.mapTo(mutableSetOf()) { it.id }
                for (local in dao.listarTodas()) {
                    if (local.id !in idsRemotos) dao.excluir(local)
                }
            }
            null
        } catch (e: Exception) {
            "Sem conexão: usando as fases salvas no celular."
        }
    }

    suspend fun carregarOriginais(): String? {
        return try {
            for (fase in FASES_ORIGINAIS) {
                remote.salvar(fase)
            }
            dao.salvarTodas(FASES_ORIGINAIS.map { it.paraEntity() })
            null
        } catch (e: Exception) {
            "Não foi possível carregar as fases: ${e.message}"
        }
    }
}
