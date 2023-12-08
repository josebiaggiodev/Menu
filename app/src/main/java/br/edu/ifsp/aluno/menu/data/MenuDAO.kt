package br.edu.ifsp.aluno.menu.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface MenuDAO {
    @Insert
    suspend fun insert(menu: Menu)

    @Update
    suspend fun update (menu: Menu)

    @Delete
    suspend fun delete(menu: Menu)

    @Query("SELECT * FROM menu ORDER BY day")
    fun getAllOptionsOfTheMenu(): LiveData<List<Menu>>

    @Query("SELECT * FROM menu WHERE id=:id")
    fun getMenuById(id: Int): LiveData<Menu>
}