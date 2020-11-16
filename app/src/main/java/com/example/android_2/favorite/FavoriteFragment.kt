package com.example.android_2.favorite


import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.android_2.R
import com.example.android_2.databinding.FragmentFavoriteBinding
import com.example.android_2.local.FavoriteMovieProperty
import com.example.android_2.network.MovieProperty
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class FavoriteFragment : Fragment(R.layout.fragment_favorite) {
    private val viewModel by viewModels<FavoriteViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentFavoriteBinding.bind(view)

        val adapter = FavoriteAdapter()

        viewModel.movies.observe(viewLifecycleOwner){
            adapter.setMovieList(it)
            binding.apply {
                rvMovie.setHasFixedSize(true)
                rvMovie.adapter = adapter
            }
        }

        adapter.setOnItemClickListener(object : FavoriteAdapter.OnItemClickListenerFavorite{
            override fun onItemClick(favoriteMovie: FavoriteMovieProperty) {
                val movie = MovieProperty(
                    favoriteMovie.id_movie,
                    favoriteMovie.overview,
                    favoriteMovie.poster_path,
                    favoriteMovie.original_title,
                    favoriteMovie.vote_average,
                    favoriteMovie.release_date
                )
                val action = FavoriteFragmentDirections.actionNavFavoriteToNavDetail(movie)
                findNavController().navigate(action)
            }
        })
    }
}