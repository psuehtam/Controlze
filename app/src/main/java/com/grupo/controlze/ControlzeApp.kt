package com.grupo.controlze

import android.app.Application
import androidx.room.Room
import com.google.firebase.firestore.FirebaseFirestore
import com.grupo.controlze.data.local.AppDatabase
import com.grupo.controlze.data.remote.FaseRemoteDataSource
import com.grupo.controlze.data.repository.FaseRepository
import com.grupo.controlze.data.repository.RankingRepository

class ControlzeApp : Application() {
    val database: AppDatabase by lazy {
        Room.databaseBuilder(this, AppDatabase::class.java, "controlze.db").build()
    }

    val faseRepository: FaseRepository by lazy {
        FaseRepository(database.faseDao(), FaseRemoteDataSource(FirebaseFirestore.getInstance()))
    }

    val rankingRepository: RankingRepository by lazy {
        RankingRepository(database.rankingDao())
    }
}
