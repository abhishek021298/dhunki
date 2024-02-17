package com.example.technomusic.mvvm.view.adapter

import android.content.Context
import android.graphics.drawable.BitmapDrawable
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.example.technomusic.data.model.Album
import com.example.technomusic.databinding.ItemAlbumViewBinding
import com.example.technomusic.utils.PaletteUtils

class AlbumAdapter(private val albumList: List<Album>, private val context: Context) : RecyclerView.Adapter<AlbumAdapter.ViewHolder>() {
private val TAG = "AlbumAdapter"

    inner class ViewHolder(val binding: ItemAlbumViewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Album) {
            with(binding) {
                album = item
                if (item.defaultImageId == -1) {
                    ivAlbumImage.setImageURI(item.albumUri.toUri())
                } else {
                    ivAlbumImage.setImageResource(item.defaultImageId)
                }
                Log.e(TAG, "bind: $item", )
                val backgroundGradiant = PaletteUtils.getPrimaryColorGradientWithLowerBlack(item.albumUri.toUri(), context)
                val gradientDrawable = BitmapDrawable(context.resources, backgroundGradiant)
                cardView.background = gradientDrawable
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemAlbumViewBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return albumList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = albumList[position]
        holder.bind(data)
    }
}