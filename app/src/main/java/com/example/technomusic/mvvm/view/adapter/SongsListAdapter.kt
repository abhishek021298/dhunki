package com.example.technomusic.mvvm.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.example.technomusic.data.model.Song
import com.example.technomusic.databinding.ItemSongsViewBinding


class SongsListAdapter(private val list: List<Song>, private val onItemClick: (Song) -> Unit) : RecyclerView.Adapter<SongsListAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemSongsViewBinding, private val onItemClicked: (Int) -> Unit) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                onItemClicked(bindingAdapterPosition)
            }
        }

        fun bind(item: Song) {
            with(binding) {
                songs = item
                if (item.defaultImageId == -1) {
                    ivSongImage.setImageURI(item.albumUri.toUri())
                } else {
                    ivSongImage.setImageResource(item.defaultImageId)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemSongsViewBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding) {
            onItemClick(list[it])
        }
    }


    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = list[position]
        holder.bind(data)

    }
}