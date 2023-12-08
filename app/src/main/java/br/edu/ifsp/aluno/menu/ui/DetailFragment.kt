package br.edu.ifsp.aluno.menu.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import br.edu.ifsp.aluno.menu.R
import br.edu.ifsp.aluno.menu.data.Menu
import br.edu.ifsp.aluno.menu.databinding.FragmentDetailBinding
import br.edu.ifsp.aluno.menu.viewmodel.MenuViewModel
import com.google.android.material.snackbar.Snackbar

class DetailFragment : Fragment() {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    lateinit var menu: Menu

    lateinit var dayEditText: EditText
    lateinit var meatEditText: EditText
    lateinit var saladEditText: EditText

    lateinit var viewModel: MenuViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this).get(MenuViewModel::class.java)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dayEditText = binding.commonLayout.editTextDay
        meatEditText = binding.commonLayout.editTextMeat
        saladEditText = binding.commonLayout.editTextSalad

        val idMenu = requireArguments().getInt("idMenu")

        viewModel.getMenuById(idMenu)

        viewModel.menu.observe(viewLifecycleOwner) { result ->
            result?.let {
                menu = result
                dayEditText.setText(menu.day)
                meatEditText.setText(menu.meat)
                saladEditText.setText(menu.salad)
            }
        }

        val menuHost: MenuHost = requireActivity()

        menuHost.addMenuProvider(object: MenuProvider {
            override fun onCreateMenu(menu: android.view.Menu, menuInflater: MenuInflater) {
                // Add menu items here
                menuInflater.inflate(R.menu.detail_menu, menu)
            }
            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                // Handle the menu selection
                return when (menuItem.itemId) {
                    R.id.action_updateMenu -> {
                        menu.day = dayEditText.text.toString()
                        menu.meat = meatEditText.text.toString()
                        menu.salad = saladEditText.text.toString()
                        viewModel.update(menu)
                        Snackbar.make(binding.root, "Opção do menu alterada", Snackbar.LENGTH_SHORT).show()
                        findNavController().popBackStack()
                        true
                    }
                    R.id.action_deleteMenu ->{
                        viewModel.delete(menu)
                        Snackbar.make(binding.root, "Opção do menu apagada", Snackbar.LENGTH_SHORT).show()
                        findNavController().popBackStack()
                        true
                    }
                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }
}