package com.example.technomusic.mvvm.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.technomusic.data.model.Artist
import com.example.technomusic.databinding.ItemArtistViewBinding

class ArtistAdapter(private val artistList: List<Artist>, private val context: Context) : RecyclerView.Adapter<ArtistAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemArtistViewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Artist) {
            with(binding) {
                artist = item
               // ivArtistImage.setImageResource(item.defaultImageId)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemArtistViewBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return artistList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = artistList[position]
        holder.bind(data)
    }
}