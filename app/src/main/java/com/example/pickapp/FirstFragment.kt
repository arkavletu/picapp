package com.example.pickapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.pickapp.adapter.CategoriesAdapter
import com.example.pickapp.databinding.FragmentFirstBinding
import com.example.pickapp.viewModel.AppViewModel

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
   val viewModel by viewModels<AppViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)

        viewModel.clickedCategory.observe(viewLifecycleOwner){array->
            val direction = FirstFragmentDirections.actionFirstFragmentToSecondFragment(array)
            findNavController().navigate(direction)
        }
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val items = viewModel.data
        val adapter = CategoriesAdapter(viewModel)
binding.categories.adapter  = adapter
    adapter.submitList(items)}

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}