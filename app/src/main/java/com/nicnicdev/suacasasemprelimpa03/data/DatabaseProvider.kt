package com.nicnicdev.suacasasemprelimpa03.data

import android.content.Context
import androidx.room.Room

object DatabaseProvider {
    private var INSTANCE: AppDatabase? = null

    fun getDatabase(context: Context): AppDatabase{
        if (INSTANCE==null){
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "sua_casa_sempre_limpa_db"
            ).build()
        }
        return INSTANCE!!
    }
}