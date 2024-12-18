package com.example.ucp2.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.ucp2.data.dao.DokterDao
import com.example.ucp2.data.dao.JadwalDao
import com.example.ucp2.data.entity.Dokter
import com.example.ucp2.data.entity.Jadwal

@Database(entities = [Dokter::class, Jadwal::class], version = 1, exportSchema = false)
abstract class HealtDatabase : RoomDatabase() {
    abstract fun dokterDao(): DokterDao
    abstract fun jadwalDao(): JadwalDao

    companion object{
    @Volatile
        private var INSTANCE: HealtDatabase? = null

        fun getDatabase(context: Context): HealtDatabase {
            return(INSTANCE ?: synchronized(this){
                Room.databaseBuilder(
                    context.applicationContext,
                    HealtDatabase::class.java,
                    "healt_database"
                ).build().also { INSTANCE = it }
            })
        }
    }

}