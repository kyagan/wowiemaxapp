package com.kaanyagan.wowiemax.ui.filteredOrSortedMovies

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.kaanyagan.wowiemax.R
import com.kaanyagan.wowiemax.data.entity.model.Movie
import com.kaanyagan.wowiemax.data.entity.state.SpecialMovieListState
import com.kaanyagan.wowiemax.databinding.ActivityFilteredOrSortedMoviesBinding
import com.kaanyagan.wowiemax.showToast
import com.kaanyagan.wowiemax.ui.adapter.MovieListAdapter
import com.kaanyagan.wowiemax.ui.detail.MovieDetailActivity
import com.kaanyagan.wowiemax.ui.main.MainActivity
import kotlinx.coroutines.launch

class FilteredOrSortedMoviesActivity : AppCompatActivity() {

    private lateinit var binding:ActivityFilteredOrSortedMoviesBinding
    private val viewModel:FilteredOrSortedMoviesViewModel by viewModels()

    companion object{
        const val MOVIE = "MOVIE"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFilteredOrSortedMoviesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val movies = intent.getParcelableArrayListExtra<Movie>(MainActivity.MOVIES)
        val movieListTitle = intent.getStringExtra(MainActivity.MOVIE_LIST_TITLE)
        binding.tvListTitle.text = movieListTitle
        viewModel.getMovies(movies)
        observeSpecialMovieListState()
        listeners()

    }

    private fun listeners() {
        binding.ivBackArrow.setOnClickListener {
            finish()
        }
    }

    private fun observeSpecialMovieListState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED){
                viewModel.specialMovieListState.collect{
                    when(it){
                        SpecialMovieListState.Idle->{}
                        SpecialMovieListState.Empty->{}
                        is SpecialMovieListState.Result->{
                            binding.rvSpeailMovies.adapter = MovieListAdapter(this@FilteredOrSortedMoviesActivity,it.movies){
                                val intent = Intent(this@FilteredOrSortedMoviesActivity, MovieDetailActivity::class.java)
                                intent.putExtra(MOVIE, it)
                                startActivity(intent)
                            }
                        }
                        is SpecialMovieListState.Error->{
                            showToast(getString(R.string.error))
                        }
                    }
                }
            }
        }
    }
}