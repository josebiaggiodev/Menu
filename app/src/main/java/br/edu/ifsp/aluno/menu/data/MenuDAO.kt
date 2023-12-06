package br.edu.ifsp.aluno.menu.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MenuDAO {
    @Insert
    suspend fun insert(menu: Menu)
    @Query("SELECT * FROM menu ORDER BY day")
    fun getAllOptionsOfTheMenu(): LiveData<List<Menu>>
}