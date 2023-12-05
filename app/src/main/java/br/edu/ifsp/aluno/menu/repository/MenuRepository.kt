package br.edu.ifsp.aluno.menu.repository

import br.edu.ifsp.aluno.menu.data.Menu
import br.edu.ifsp.aluno.menu.data.MenuDAO

class MenuRepository (private val menuDAO: MenuDAO) {
    suspend fun insert(menu: Menu){
        menuDAO.insert(menu)
    }
}