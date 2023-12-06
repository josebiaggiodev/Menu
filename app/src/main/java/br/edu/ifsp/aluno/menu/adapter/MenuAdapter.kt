package br.edu.ifsp.aluno.menu.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.edu.ifsp.aluno.menu.data.Menu
import br.edu.ifsp.aluno.menu.databinding.MenuCellBinding

class MenuAdapter():
    RecyclerView.Adapter<MenuAdapter.MenuViewHolder>() {
    private lateinit var binding: MenuCellBinding
    private var menuList = ArrayList<Menu>()
    fun updateList(newList: ArrayList<Menu> ){
        menuList = newList
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MenuViewHolder {
        binding = MenuCellBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MenuViewHolder(binding)
    }
    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        holder.dayVH.text = menuList[position].day
    }
    override fun getItemCount(): Int {
        return menuList.size
    }
    inner class MenuViewHolder(view:MenuCellBinding): RecyclerView.ViewHolder(view.root)
    {
        val dayVH = view.day
    }
}