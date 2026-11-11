package com.grupo.controlze.data.remote

import com.google.firebase.firestore.FirebaseFirestore
import com.grupo.controlze.logic.Fase
import kotlinx.coroutines.tasks.await

class FaseRemoteDataSource(firestore: FirebaseFirestore) {
    private val colecao = firestore.collection("fases")

    suspend fun buscarTodas(): List<Fase> {
        val resultado = colecao.get().await()
        return resultado.documents.mapNotNull { documento ->
            documento.toObject(FaseDto::class.java)?.paraFase(documento.id)
        }
    }

    suspend fun buscarIds(): List<String> {
        return colecao.get().await().documents.map { it.id }
    }

    suspend fun salvar(fase: Fase) {
        colecao.document(fase.id).set(fase.paraDto()).await()
    }

    suspend fun excluir(id: String) {
        colecao.document(id).delete().await()
    }
}
