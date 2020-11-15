package com.example.android_2.detail

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.android_2.R
import com.example.android_2.databinding.FragmentDetailsBinding

class DetailFragment : Fragment (R.layout.fragment_details){
    private  val args by navArgs<DetailFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        val binding = FragmentDetailsBinding.bind(view)
        
        binding.apply { 
            val movie = args.movie
            Glide.with(this@DetailFragment)
                    .load("${movie.base_image_url}${movie.poster_path}")
                    .error(R.drawable.ic_error)
                    .listener(object : RequestListener<Drawable>{
                        override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                            progressBar.isVisible = false
                            return false
                        }

                        override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                            progressBar.isVisible = false
                            tvDescription.isVisible = true
                            tvMovieTitle.isVisible = true
                            return false
                        }
                    }).into(ivMoviePoster)

                    tvDescription.text = movie.overview
                    tvMovieTitle.text = movie.original_title
                        

        }
    }
}