package com.grupo.controlze.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
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
