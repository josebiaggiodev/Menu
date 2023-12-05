package br.edu.ifsp.aluno.menu.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Menu::class], version = 1)
abstract class MenuDatabase: RoomDatabase() {
    abstract fun menuDAO(): MenuDAO

    companion object {
        @Volatile
        private var INSTANCE: MenuDatabase? = null
        fun getDatabase(context: Context): MenuDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MenuDatabase::class.java,
                    "menuroom.db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}