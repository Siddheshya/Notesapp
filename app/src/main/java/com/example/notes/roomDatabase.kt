package com.example.notes

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import java.time.chrono.HijrahChronology.INSTANCE

@Database(entities = arrayOf(Note::class), version = 1, exportSchema = false)
abstract class roomDatabase: RoomDatabase() {
    abstract fun getNotesDao():NoteDao
    companion object {
        @Volatile
        private var INSTANCE:roomDatabase?=null
        fun getDatabase(context:Context):roomDatabase{
            return INSTANCE?: synchronized(this){
                val instance= Room.databaseBuilder(
                    context.applicationContext,
                    roomDatabase::class.java,
                    "note_database"
                ).build()
                INSTANCE=instance
                instance
            }
        }
    }
}