package com.grupo.controlze.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [FaseEntity::class, RankingEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun faseDao(): FaseDao
    abstract fun rankingDao(): RankingDao
}
