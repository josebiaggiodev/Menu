package br.edu.ifsp.aluno.menu.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import br.edu.ifsp.aluno.menu.data.Menu
import br.edu.ifsp.aluno.menu.data.MenuDatabase
import br.edu.ifsp.aluno.menu.repository.MenuRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MenuViewModel(application: Application): AndroidViewModel(application) {
    private val repository: MenuRepository
    lateinit var menu : LiveData<Menu>
    init {
        val dao = MenuDatabase.getDatabase(application).menuDAO()
        repository = MenuRepository(dao)
    }
    fun insert(menu: Menu) = viewModelScope.launch(Dispatchers.IO){
        repository.insert(menu)
    }
}