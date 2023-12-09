package br.edu.ifsp.aluno.menu.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import br.edu.ifsp.aluno.menu.data.Menu
import br.edu.ifsp.aluno.menu.databinding.MenuCellBinding

class MenuAdapter():
    RecyclerView.Adapter<MenuAdapter.MenuViewHolder>(), Filterable {
    private lateinit var binding: MenuCellBinding
    private var menuList = ArrayList<Menu>()
    var menuListFilterable = ArrayList<Menu>()
    var listener: MenuListener?=null

    fun updateList(newList: ArrayList<Menu>) {
        menuList = newList
        menuListFilterable = menuList
        notifyDataSetChanged()
    }

    fun setClickListener(listener: MenuListener) {
        this.listener = listener
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
        init {
            view.root.setOnClickListener {
                listener?.onItemClick(adapterPosition)
            }
        }
    }

    interface MenuListener {
        fun onItemClick(pos: Int)
    }

    override fun getFilter(): Filter {
        return object : Filter(){
            override fun performFiltering(p0: CharSequence?): FilterResults {
                if (p0.toString().isEmpty()) {
                    menuListFilterable.clear()
                    menuListFilterable.addAll(menuList)
                } else {
                    val resultList = ArrayList<Menu>()
                    for (row in menuList) {
                        if (row.day.lowercase().contains(p0.toString().lowercase())) {
                            resultList.add(row)
                        }
                    }

                    menuListFilterable.clear()
                    menuListFilterable.addAll(resultList)
                }

                val filterResults = FilterResults()
                filterResults.values = menuListFilterable
                return filterResults
            }
            override fun publishResults(p0: CharSequence?, p1: FilterResults?) {
                menuListFilterable = p1?.values as ArrayList<Menu>
                notifyDataSetChanged()
            }
        }
    }
}