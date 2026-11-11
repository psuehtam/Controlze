package com.grupo.controlze.data.local

import androidx.room3.Dao
import androidx.room3.Delete
import androidx.room3.Insert
import androidx.room3.OnConflictStrategy
import androidx.room3.Query
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
