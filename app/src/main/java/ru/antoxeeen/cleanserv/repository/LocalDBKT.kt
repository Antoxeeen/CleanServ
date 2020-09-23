package ru.antoxeeen.cleanserv.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [DataKT::class], version = 2, exportSchema = true)
abstract class LocalDBKT : RoomDatabase() {
    abstract fun dataKtDAO(): DataKtDAO
    companion object {
        @Volatile
        private var INSTANCE: LocalDBKT? = null

        fun getDatabaseInstance(context: Context): LocalDBKT {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        LocalDBKT::class.java,
                        "localDBKT"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}