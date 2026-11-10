package com.grupo.controlze.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "fases")
data class FaseEntity(
    @PrimaryKey val id: String,
    val ordem: Double,
    val categoria: String,
    val tipo: String,
    val pergunta: String,
    val codigo: String?,
    val alternativa1: String,
    val alternativa2: String,
    val alternativa3: String,
    val alternativa4: String,
    val resposta: String,
    val alvo: String?,
    val original: Boolean
)
