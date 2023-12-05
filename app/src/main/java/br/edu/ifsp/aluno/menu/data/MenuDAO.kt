package br.edu.ifsp.aluno.menu.data

import androidx.room.Dao
import androidx.room.Insert

@Dao
interface MenuDAO {
    @Insert
    suspend fun insert(menu: Menu)
}