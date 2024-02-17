package com.example.technomusic.mvvm.view.adapter

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.example.technomusic.data.model.Genres
import com.example.technomusic.databinding.ItemGenresViewBinding

class GenresAdapter(private val genresList: List<Genres>, private val context: Context) :
    RecyclerView.Adapter<GenresAdapter.ViewHolder>() {
    private val TAG = "GenresAdapter"

    inner class ViewHolder(val binding: ItemGenresViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Genres) {
            with(binding) {
                genres = item
                if (item.defaultImageId == -1) {
                    ivAlbumImage.setImageURI(item.albumUri.toUri())
                } else {
                    ivAlbumImage.setImageResource(item.defaultImageId)
                }
                Log.e(TAG, "bind: $item")
                cardView.setCardBackgroundColor(ColorStateList.valueOf(Color.parseColor(item.primaryColor)))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemGenresViewBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return genresList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = genresList[position]
        holder.bind(data)
    }
}