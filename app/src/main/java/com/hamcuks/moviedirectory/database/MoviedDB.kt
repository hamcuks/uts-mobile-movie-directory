package com.hamcuks.moviedirectory.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.hamcuks.moviedirectory.model.ResultMovie
import com.hamcuks.moviedirectory.service.FavouriteDao

@Database(
    entities = [ResultMovie::class],
    version = 1
)
abstract class MoviedDB : RoomDatabase() {
    abstract fun favouriteDao() : FavouriteDao

    companion object {
        @Volatile
        private var INSTANCE: MoviedDB? = null

        fun getDb(context: Context) : MoviedDB {
            val temp = INSTANCE

            if(temp != null) {
                return temp
            }

            synchronized(MoviedDB::class.java) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MoviedDB::class.java,
                    "room_database"
                ).build()

                INSTANCE = instance
                return instance as MoviedDB
            }
        }
    }
}