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
) {
    override fun toString(): String {
        return "Menu(id=$id, day='$day', meat='$meat', salad='$salad')"
    }

    override fun equals(other: Any?): Boolean {
        return if (this === other) {
            true
        } else if (other is Menu) {
            this.id == other.id && this.day == other.day && this.meat == other.meat && this.salad == other.salad
        } else {
            false
        }
    }
}