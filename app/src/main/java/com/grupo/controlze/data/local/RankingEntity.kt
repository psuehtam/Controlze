package com.grupo.controlze.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ranking")
data class RankingEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nomeJogador: String,
    val totalFases: Int,
    val erros: Int,
    val tempoSegundos: Long
)
