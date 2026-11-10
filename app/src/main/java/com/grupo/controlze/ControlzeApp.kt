package com.grupo.controlze

import android.app.Application
import androidx.room.Room
import com.grupo.controlze.data.local.AppDatabase

class ControlzeApp : Application() {
    val database: AppDatabase by lazy {
        Room.databaseBuilder(this, AppDatabase::class.java, "controlze.db").build()
    }
}
