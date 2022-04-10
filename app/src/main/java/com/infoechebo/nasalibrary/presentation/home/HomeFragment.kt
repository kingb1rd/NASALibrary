package com.infoechebo.nasalibrary.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.infoechebo.nasalibrary.databinding.FragmentHomeBinding
import com.infoechebo.nasalibrary.domain.model.NasaImage
import com.infoechebo.nasalibrary.presentation.adapter.NasaImageAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class HomeFragment : Fragment(), NasaImageAdapter.NasaImageClickListener {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: NasaImageAdapter
    private val viewModel by viewModels<HomeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setAdapter()

        binding.btnSearch.setOnClickListener {
            viewModel.onSearch(binding.tieSearch.text.toString())
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.homeState.collectLatest { state ->
                when (state) {
                    is HomeViewModel.HomeState.Success -> {
                        binding.progressBar.visibility = View.INVISIBLE
                        adapter.submitList(state.nasaImages)
                    }
                    is HomeViewModel.HomeState.Error -> {
                        binding.progressBar.visibility = View.INVISIBLE
                        makeToast(state.message)
                    }
                    is HomeViewModel.HomeState.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }
                    else -> Unit
                }
            }
        }
    }

    private fun makeToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    private fun setAdapter() {
        adapter = NasaImageAdapter(this)
        binding.rvNasaMedia.adapter = adapter
    }

    override fun onClick(nasaImage: NasaImage) {
        val action = HomeFragmentDirections.actionHomeFragmentToDetailsFragment(nasaImage)
        findNavController().navigate(action)
    }
}