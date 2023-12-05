package br.edu.ifsp.aluno.menu.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Menu (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    var day: String,
    var meat: String,
    var salad: String
)