package com.kaanyagan.wowiemax.ui.main

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.viewpager2.widget.ViewPager2
import com.example.movieproject.MovieListAdapter
import com.kaanyagan.wowiemax.data.entity.model.Categorie
import com.kaanyagan.wowiemax.data.entity.state.SlideMovieState
import com.kaanyagan.wowiemax.data.state.MovieListState
import com.kaanyagan.wowiemax.databinding.ActivityMainBinding
import com.kaanyagan.wowiemax.showToast
import com.kaanyagan.wowiemax.ui.adapter.SlideMovieAdapter
import com.kaanyagan.wowiemax.ui.login.LoginActivity
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModels()
    private lateinit var sharedPreferences: SharedPreferences

    companion object {
        const val MOVIE = "MOVIE"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.getMoviesByCategory(Categorie.Aksiyon)
        viewModel.getMoviesByPopularity()
        viewModel.getMoviesByStreamer("Wowie")
        viewModel.getSlideMovie("Wowie")
        observeMovieListByCategory()
        observeMovieListByPopularity()
        observeMovieListByStreamer()
        observeSlideMovieState()

        listeners()



    }

    private fun observeMovieListByCategory() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.movieListByCategoryState.collect {
                    when (it) {
                        is MovieListState.Idle -> {}
                        is MovieListState.Loading -> {
                            binding.categoryProgressBar.isVisible = true
                        }

                        is MovieListState.isEmpty -> {
                            binding.categoryProgressBar.isVisible = false
                            binding.twEmptyCategoryMovies.isVisible = true
                        }
                        is MovieListState.Success -> {
                            binding.categoryProgressBar.isVisible = false
                            binding.rwMovieCategory.adapter =
                                MovieListAdapter(this@MainActivity, it.movies) { movies ->
                                    val intent = Intent(this@MainActivity, MainActivity::class.java)
                                    intent.putExtra(MOVIE, movies)
                                    startActivity(intent)

                                }

                        }

                        is MovieListState.Error -> {
                            showToast("Unexpected error occurred")
                        }
                    }
                }
            }
        }
    }

    private fun observeMovieListByPopularity() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.movieListByPopularityState.collect {
                    when (it) {
                        is MovieListState.Idle -> {}
                        is MovieListState.Loading -> {
                            binding.popularityProgressBar.isVisible = true
                        }

                        is MovieListState.isEmpty -> {
                            binding.categoryProgressBar.isVisible = false
                            binding.twEmptyPopularMovies.isVisible = true
                        }
                        is MovieListState.Success -> {
                            binding.popularityProgressBar.isVisible = false
                            binding.rwMoviePopularity.adapter =
                                MovieListAdapter(this@MainActivity, it.movies) { movie ->
                                    val intent = Intent(this@MainActivity, MainActivity::class.java)
                                    intent.putExtra(MOVIE, movie)
                                    startActivity(intent)

                                }
                        }

                        is MovieListState.Error -> {
                            showToast("Unexpected error occurred")
                        }
                    }
                }
            }
        }
    }

    private fun observeMovieListByStreamer() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.movieListByStreamerState.collect {
                    when (it) {
                        is MovieListState.Idle -> {}
                        is MovieListState.Loading -> {
                            binding.producerProgressBar.isVisible = true
                        }

                        is MovieListState.isEmpty -> {
                            binding.producerProgressBar.isVisible = false
                            binding.twEmptyProducerMovies.isVisible = true
                        }
                        is MovieListState.Success -> {
                            binding.producerProgressBar.isVisible = false
                            binding.vpMovieStreamer.adapter =
                                MovieListAdapter(this@MainActivity, it.movies) { movie ->
                                    val intent = Intent(this@MainActivity, MainActivity::class.java)
                                    intent.putExtra(MOVIE, movie)
                                    startActivity(intent)
                                }

                        }

                        is MovieListState.Error -> {
                            showToast("Unexpected error occurred")
                        }
                    }
                }
            }
        }
    }

    private fun observeSlideMovieState() {
        lifecycleScope.launch{
            repeatOnLifecycle(Lifecycle.State.CREATED){
                viewModel.slideMovieState.collect{
                    when(it){
                        is SlideMovieState.Idle->{}
                        is SlideMovieState.Loading->{
                            binding.producerProgressBar.isVisible = true
                        }
                        is SlideMovieState.IsEmpty->{
                            binding.producerProgressBar.isVisible = false
                            binding.twEmptyProducerMovies.isVisible = true
                        }
                        is SlideMovieState.Success->{
                            binding.producerProgressBar.isVisible = false
                            binding.vpMovieStreamer.adapter =
                                SlideMovieAdapter(this@MainActivity, it.movies) { movie ->
                                binding.vpMovieStreamer.orientation = ViewPager2.ORIENTATION_HORIZONTAL
                                val intent = Intent(this@MainActivity, MainActivity::class.java)
                                intent.putExtra(MOVIE, movie)
                                startActivity(intent)
                            }
                            binding.indicator.setViewPager(binding.vpMovieStreamer)
                        }
                        is SlideMovieState.Error->{}
                    }
                }
            }
        }
    }

    fun listeners(){
        binding.iwMenu.setOnClickListener {
            if(!binding.linearLayoutMenu.isVisible){

                val menu = binding.linearLayoutMenu

                menu.visibility = View.VISIBLE

            }
            else
                binding.linearLayoutMenu.visibility = View.GONE
        }
        binding.tvExit.setOnClickListener {
            sharedPreferences = getSharedPreferences(LoginActivity.LOGIN_REMEMBER_ME, Context.MODE_PRIVATE)
            sharedPreferences.edit().putBoolean(LoginActivity.LOGIN_REMEMBER_ME,false).apply()
            val intent = Intent(this@MainActivity,LoginActivity::class.java)
            startActivity(intent)
        }
    }

}