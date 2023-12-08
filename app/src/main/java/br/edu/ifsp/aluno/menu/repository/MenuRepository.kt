package br.edu.ifsp.aluno.menu.repository

import androidx.lifecycle.LiveData
import br.edu.ifsp.aluno.menu.data.Menu
import br.edu.ifsp.aluno.menu.data.MenuDAO

class MenuRepository (private val menuDAO: MenuDAO) {
    suspend fun insert(menu: Menu){
        menuDAO.insert(menu)
    }

    suspend fun update(menu: Menu){
        menuDAO.update(menu)
    }

    suspend fun delete(menu: Menu){
        menuDAO.delete(menu)
    }

    fun getAllOptionsOfTheMenu(): LiveData<List<Menu>> {
        return menuDAO.getAllOptionsOfTheMenu()
    }

    fun getMenuById(id: Int): LiveData<Menu>{
        return menuDAO.getMenuById(id)
    }
}