package br.edu.ifsp.aluno.menu.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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
    lateinit var contatoAdapter: MenuAdapter
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
    private fun configureRecyclerView()
    {
        viewModel = ViewModelProvider(this).get(MenuViewModel::class.java)
        viewModel.allOptionsOfTheMenu.observe(viewLifecycleOwner) { list ->
            list?.let {
                contatoAdapter.updateList(list as ArrayList<Menu>)
            }
        }
        val recyclerView = binding.recyclerview
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        contatoAdapter = MenuAdapter()
        recyclerView.adapter = contatoAdapter
    }
}