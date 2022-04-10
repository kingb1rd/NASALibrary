package com.infoechebo.nasalibrary.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.infoechebo.nasalibrary.common.GlideRequestOptions
import com.infoechebo.nasalibrary.databinding.ItemNasaImageBinding
import com.infoechebo.nasalibrary.domain.model.NasaImage

class NasaImageAdapter(private val clickListener: NasaImageClickListener) :
    ListAdapter<NasaImage, NasaImageAdapter.ViewHolder>(DiffCallback()) {

    interface NasaImageClickListener {
        fun onClick(nasaImage: NasaImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemNasaImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), clickListener)
    }

    class ViewHolder(private val binding: ItemNasaImageBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(nasaImage: NasaImage, clickListener: NasaImageClickListener) {
            Glide.with(itemView)
                .applyDefaultRequestOptions(GlideRequestOptions.getGlideRequestOptions())
                .load(nasaImage.href)
                .centerCrop()
                .into(binding.ivNasaImage)

            binding.root.setOnClickListener {
                clickListener.onClick(nasaImage)
            }
        }
    }
}

class DiffCallback : DiffUtil.ItemCallback<NasaImage>() {
    override fun areItemsTheSame(oldItem: NasaImage, newItem: NasaImage) =
        oldItem.nasa_id == newItem.nasa_id

    override fun areContentsTheSame(oldItem: NasaImage, newItem: NasaImage) = oldItem == newItem
}