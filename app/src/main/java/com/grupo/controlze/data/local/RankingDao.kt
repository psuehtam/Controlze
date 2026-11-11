package com.grupo.controlze.data.local

import androidx.room3.Dao
import androidx.room3.Delete
import androidx.room3.Insert
import androidx.room3.Query
import androidx.room3.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface RankingDao {
    @Query("SELECT * FROM ranking")
    fun observarTodos(): Flow<List<RankingEntity>>

    @Insert
    suspend fun inserir(item: RankingEntity)

    @Update
    suspend fun atualizar(item: RankingEntity)

    @Delete
    suspend fun excluir(item: RankingEntity)

    @Query("DELETE FROM ranking")
    suspend fun excluirTodos()
}
