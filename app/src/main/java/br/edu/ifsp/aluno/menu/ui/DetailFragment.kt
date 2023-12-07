package br.edu.ifsp.aluno.menu.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import br.edu.ifsp.aluno.menu.data.Menu
import br.edu.ifsp.aluno.menu.databinding.FragmentDetailBinding
import br.edu.ifsp.aluno.menu.viewmodel.MenuViewModel

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
    }
}