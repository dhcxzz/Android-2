package com.example.android_2.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.android_2.R
import com.example.android_2.databinding.ItemMovieBinding
import com.example.android_2.network.MovieProperty

class MovieAdapter(private  val listener : OnItemClickListener) : PagingDataAdapter<MovieProperty,MovieAdapter.MovieViewHolder>(COMPARATOR){

    inner class MovieViewHolder(private val binding: ItemMovieBinding)
        :RecyclerView.ViewHolder(binding.root){
        init{
            binding.root.setOnClickListener{
                val position = bindingAdapterPosition
                if(position != RecyclerView.NO_POSITION){
                    val item = getItem(position)
                    if (item!=null){
                        listener.onItemCLick(item)
                    }
                }
            }
        }


        fun bind(movie:MovieProperty){
            with(binding){
                Glide.with(itemView)
                    .load("${movie.base_image_url}${movie.poster_path}")
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .error(R.drawable.ic_error)
                    .into(ivMoviePoster)

                tvMovieTitle.text = movie.original_title
            }
        }
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null){
            holder.bind(currentItem)
        }
    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<MovieProperty>() {
            override fun areItemsTheSame(oldItem: MovieProperty, newItem: MovieProperty): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: MovieProperty, newItem: MovieProperty): Boolean =
                oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    interface OnItemClickListener{
        fun onItemCLick(movie:MovieProperty)
    }
}