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
    private val originaisPorId = FASES_ORIGINAIS.associateBy { it.id }

    val fases: Flow<List<Fase>> = dao.observarTodas().map { lista ->
        lista.mapNotNull { it.paraFase() }
    }

    suspend fun sincronizar(): String? {
        return try {
            atualizarOriginaisLocais()
            val idsRemotos = remote.buscarIds()
            var remotas = remote.buscarTodas()
            if (idsRemotos.any { it.startsWith("original-") } &&
                (idsRemotos.any { it.startsWith("original-") && it !in originaisPorId } ||
                    FASES_ORIGINAIS.any { original -> remotas.find { it.id == original.id } != original })
            ) {
                salvarOriginaisRemotas(idsRemotos)
                remotas = remote.buscarTodas()
            }
            if (remotas.isNotEmpty()) {
                dao.salvarTodas(remotas.map { it.paraEntity() })
                val idsAtuais = remotas.mapTo(mutableSetOf()) { it.id }
                for (local in dao.listarTodas()) {
                    if (local.id !in idsAtuais) dao.excluir(local)
                }
            }
            null
        } catch (e: Exception) {
            "Sem conexão: usando as fases salvas no celular."
        }
    }

    suspend fun carregarOriginais(): String? {
        return try {
            salvarOriginaisRemotas(remote.buscarIds())
            atualizarOriginaisLocais(forcar = true)
            null
        } catch (e: Exception) {
            "Não foi possível carregar as fases: ${e.message}"
        }
    }

    private suspend fun salvarOriginaisRemotas(idsRemotos: List<String>) {
        for (id in idsRemotos) {
            if (id.startsWith("original-") && id !in originaisPorId) remote.excluir(id)
        }
        for (fase in FASES_ORIGINAIS) remote.salvar(fase)
    }

    private suspend fun atualizarOriginaisLocais(forcar: Boolean = false) {
        val locais = dao.listarTodas()
        if (!forcar && locais.none { it.original || it.id.startsWith("original-") }) return
        val atuais = FASES_ORIGINAIS.map { it.paraEntity() }
        if (forcar || atuais.any { atual -> locais.find { it.id == atual.id } != atual }) {
            dao.salvarTodas(atuais)
        }
        for (local in locais) {
            if (local.id.startsWith("original-") && local.id !in originaisPorId) dao.excluir(local)
        }
    }
}
