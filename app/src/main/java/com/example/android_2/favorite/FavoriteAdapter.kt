package com.example.android_2.favorite

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.android_2.R
import com.example.android_2.databinding.ItemMovieBinding
import com.example.android_2.local.FavoriteMovieProperty

class FavoriteAdapter : RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {

    private lateinit var list : List<FavoriteMovieProperty>

    private var onItemClickListenerFavorite: OnItemClickListenerFavorite? = null

    fun setOnItemClickListener(onItemClickListenerFavorite: OnItemClickListenerFavorite){
        this.onItemClickListenerFavorite = onItemClickListenerFavorite
    }

    fun setMovieList(list: List<FavoriteMovieProperty>){
        this.list = list
        notifyDataSetChanged()
    }

    inner class FavoriteViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(favoriteMovie: FavoriteMovieProperty) {
            with(binding) {

                Glide.with(itemView)
                    .load("${favoriteMovie.base_image_url}${favoriteMovie.poster_path}")
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .error(R.drawable.ic_error)
                    .into(ivMoviePoster)
                tvMovieTitle.text = favoriteMovie.original_title
                binding.root.setOnClickListener { onItemClickListenerFavorite?.onItemClick(favoriteMovie) }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteViewHolder(binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        Log.e("adapter", "Masuk bind view holder")
        holder.bind(list[position])
    }

    interface OnItemClickListenerFavorite {
        fun onItemClick(favoriteMovie: FavoriteMovieProperty)
    }
}