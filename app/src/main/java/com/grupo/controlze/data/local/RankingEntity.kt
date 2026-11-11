package com.grupo.controlze.data.local

import androidx.room3.Entity
import androidx.room3.PrimaryKey

@Entity(tableName = "ranking")
data class RankingEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nomeJogador: String,
    val totalFases: Int,
    val erros: Int,
    val tempoSegundos: Long
)
