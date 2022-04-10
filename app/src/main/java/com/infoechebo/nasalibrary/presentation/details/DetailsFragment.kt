package com.infoechebo.nasalibrary.presentation.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.infoechebo.nasalibrary.common.GlideRequestOptions
import com.infoechebo.nasalibrary.databinding.FragmentDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment() {
    private lateinit var binding: FragmentDetailsBinding
    private val args: DetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadDetails(view)
    }

    private fun loadDetails(view: View) {
        args.nasaImage.href?.let {
            Glide.with(view)
                .applyDefaultRequestOptions(GlideRequestOptions.getGlideRequestOptions())
                .load(it)
                .centerCrop()
                .into(binding.ivNasaImageDetail)
        }

        args.nasaImage.title?.let { binding.tvImageTitle.text = it }
        args.nasaImage.description?.let { binding.tvImageDescription.text = it }
        args.nasaImage.location?.let { binding.tvLocation.text = it }
    }
}