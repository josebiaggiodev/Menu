package br.edu.ifsp.aluno.menu.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import br.edu.ifsp.aluno.menu.R
import br.edu.ifsp.aluno.menu.adapter.MenuAdapter
import br.edu.ifsp.aluno.menu.data.Menu
import br.edu.ifsp.aluno.menu.databinding.FragmentListBinding
import br.edu.ifsp.aluno.menu.viewmodel.MenuViewModel

class ListFragment : Fragment(){
    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!
    lateinit var menuAdapter: MenuAdapter
    lateinit var viewModel: MenuViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListBinding.inflate(inflater, container, false)

        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_registerFragment) }
        configureRecyclerView()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val menuHost: MenuHost = requireActivity()

        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: android.view.Menu, menuInflater: MenuInflater) {

                menuInflater.inflate(R.menu.main_menu, menu)

                val searchView = menu.findItem(R.id.action_search).actionView as SearchView

                searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(p0: String?): Boolean {
                        return false
                    }
                    override fun onQueryTextChange(p0: String?): Boolean {
                        menuAdapter.filter.filter(p0)
                        return true
                    }
                })
            }
            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                TODO("Not yet implemented")
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    private fun configureRecyclerView() {
        viewModel = ViewModelProvider(this).get(MenuViewModel::class.java)
        viewModel.allOptionsOfTheMenu.observe(viewLifecycleOwner) { list ->
            list?.let {
                menuAdapter.updateList(list as ArrayList<Menu>)
            }
        }
        val recyclerView = binding.recyclerview
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        menuAdapter = MenuAdapter()
        recyclerView.adapter = menuAdapter

        val listener = object : MenuAdapter.MenuListener {
            override fun onItemClick(pos: Int) {
                val c = menuAdapter.menuListFilterable[pos]
                val bundle = Bundle()
                bundle.putInt("idMenu", c.id)
                findNavController().navigate(
                    R.id.action_listFragment_to_detailFragment,
                    bundle
                )
            }
        }
        menuAdapter.setClickListener(listener)
    }
}