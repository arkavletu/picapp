package com.example.pickapp

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListAdapter
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.pickapp.adapter.PicAdapter
import com.example.pickapp.databinding.FragmentSecondBinding
import com.example.pickapp.viewModel.AppViewModel

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {
    private val args by navArgs<SecondFragmentArgs>()
    private var _binding: FragmentSecondBinding? = null
    val viewModel by viewModels<AppViewModel>()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = PicAdapter(viewModel)
        this.binding.grid.adapter = adapter
        viewModel.dataReply.observe(viewLifecycleOwner, { state ->
            Log.d(TAG, "onViewCreated: ${args.hits?.size}")
            adapter.submitList(state.reply.hits)
            binding.progress.isVisible = state.loading
            binding.errorGroup.isVisible = state.error
            binding.emptyText.isVisible = state.empty
        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}