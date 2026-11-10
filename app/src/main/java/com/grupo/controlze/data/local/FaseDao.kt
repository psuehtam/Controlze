package com.grupo.controlze.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FaseDao {
    @Query("SELECT * FROM fases ORDER BY ordem")
    fun observarTodas(): Flow<List<FaseEntity>>

    @Query("SELECT * FROM fases")
    suspend fun listarTodas(): List<FaseEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun salvar(fase: FaseEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun salvarTodas(fases: List<FaseEntity>)

    @Delete
    suspend fun excluir(fase: FaseEntity)
}
