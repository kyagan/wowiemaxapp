package com.kaanyagan.wowiemax.ui.main

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.viewpager2.widget.ViewPager2
import com.kaanyagan.wowiemax.ui.adapter.MovieListAdapter
import com.kaanyagan.wowiemax.R
import com.kaanyagan.wowiemax.data.Database
import com.kaanyagan.wowiemax.data.entity.model.Category
import com.kaanyagan.wowiemax.data.entity.state.CategoryListState
import com.kaanyagan.wowiemax.data.entity.state.HeaderSlideMovieState
import com.kaanyagan.wowiemax.data.entity.state.SlideMovieState
import com.kaanyagan.wowiemax.data.state.MovieListState
import com.kaanyagan.wowiemax.databinding.ActivityMainBinding
import com.kaanyagan.wowiemax.showToast
import com.kaanyagan.wowiemax.ui.adapter.CategoryAdapter
import com.kaanyagan.wowiemax.ui.adapter.HeaderSlideMovieAdapter
import com.kaanyagan.wowiemax.ui.adapter.SlideMovieAdapter
import com.kaanyagan.wowiemax.ui.detail.MovieDetailActivity
import com.kaanyagan.wowiemax.ui.error.ErrorActivity
import com.kaanyagan.wowiemax.ui.filteredOrSortedMovies.FilteredOrSortedMoviesActivity
import com.kaanyagan.wowiemax.ui.login.LoginActivity
import com.kaanyagan.wowiemax.ui.search.SearchActivity
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    private lateinit var sharedPreferences: SharedPreferences

    companion object {
        const val MOVIE = "MOVIE"
        const val MOVIES = "MOVIES"
        const val MOVIE_LIST_TITLE = "MOVIE_LIST_TITLE"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.getCategories(Category.values())
        viewModel.getMoviesByCategory(Category.Korku)
        viewModel.getMoviesByPopularity()
        viewModel.getMoviesByStreamer(getString(R.string.wowie))
        viewModel.getSlideMovie(getString(R.string.wowie))
        viewModel.getHeaderSlideMovie(getString(R.string.wowie))

        observeMovieListByCategory()
        observeMovieListByPopularity()
        observeMovieListByStreamer()
        observeSlideMovieState()
        observeHeaderSlideMovieState()
        observeCategoryListState()

        listeners()

    }


    private fun observeCategoryListState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED){
                viewModel.categoryListState.collect{
                    when(it){
                        CategoryListState.Idle->{}
                        CategoryListState.Empty->{
                            binding.rvCategories.isVisible = false
                        }
                        is CategoryListState.Result->{
                            binding.rvCategories.adapter = CategoryAdapter(this@MainActivity,Category.values()){
                                val intent = Intent(this@MainActivity,
                                    FilteredOrSortedMoviesActivity::class.java)
                                val movies = viewModel.setMoviesByCategory(it)
                                intent.putExtra(MOVIES,movies)
                                intent.putExtra(MOVIE_LIST_TITLE,"${it.name} Filmleri")
                                startActivity(intent)
                            }
                        }
                        is CategoryListState.Error->{
                            showToast(getString(R.string.list_error))
                        }
                    }
                }
            }
        }
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
                                    val intent = Intent(this@MainActivity, MovieDetailActivity::class.java)
                                    intent.putExtra(MOVIE, movies)
                                    startActivity(intent)
                                }
                        }
                        is MovieListState.Error -> {
                            showToast(getString(R.string.list_error))
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
                                    val intent = Intent(this@MainActivity, MovieDetailActivity::class.java)
                                    intent.putExtra(MOVIE, movie)
                                    startActivity(intent)
                                }
                        }
                        is MovieListState.Error -> {
                            showToast(getString(R.string.list_error))
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
                            binding.vpSlide.adapter =
                                MovieListAdapter(this@MainActivity, it.movies) { movie ->
                                    val intent = Intent(this@MainActivity, MovieDetailActivity::class.java)
                                    intent.putExtra(MOVIE, movie)
                                    startActivity(intent)
                                }
                        }
                        is MovieListState.Error -> {
                            showToast(getString(R.string.list_error))
                        }
                    }
                }
            }
        }
    }

    private fun observeHeaderSlideMovieState() {
        lifecycleScope.launch{
            repeatOnLifecycle(Lifecycle.State.CREATED){
                viewModel.headerSlideMovieState.collect{
                    when(it){
                        is HeaderSlideMovieState.Idle->{}
                        is HeaderSlideMovieState.Loading->{
                            binding.headerSlideProgressBar.isVisible = true
                        }
                        is HeaderSlideMovieState.IsEmpty->{
                            binding.headerSlideProgressBar.isVisible = false
                            binding.twEmptyProducerMovies.isVisible = true
                        }
                        is HeaderSlideMovieState.Success->{
                            binding.headerSlideProgressBar.isVisible = false
                            binding.vpHeaderSlide.adapter = HeaderSlideMovieAdapter(this@MainActivity, it.movies) { movie ->
                                    binding.vpHeaderSlide.orientation = ViewPager2.ORIENTATION_HORIZONTAL
                                    val intent = Intent(this@MainActivity, MovieDetailActivity::class.java)
                                    intent.putExtra(MOVIE, movie)
                                    startActivity(intent)
                                }
                        }
                        is HeaderSlideMovieState.Error->{
                            showToast(getString(R.string.list_error))
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
                            binding.vpSlide.adapter =
                                SlideMovieAdapter(this@MainActivity, it.movies) { movie ->
                                binding.vpSlide.orientation = ViewPager2.ORIENTATION_HORIZONTAL
                                val intent = Intent(this@MainActivity, MovieDetailActivity::class.java)
                                intent.putExtra(MOVIE, movie)
                                startActivity(intent)
                            }
                            binding.indicator.setViewPager(binding.vpSlide)
                        }
                        is SlideMovieState.Error->{
                            showToast(getString(R.string.list_error))
                        }
                    }
                }
            }
        }
    }

    fun listeners(){

        binding.tvShowAllHorror.setOnClickListener {
            val movies = viewModel.setMoviesByCategory(Category.Korku)
            val intent = Intent(this,FilteredOrSortedMoviesActivity::class.java)
            intent.putExtra(MOVIES,movies)
            intent.putExtra(MOVIE_LIST_TITLE,"${Category.Korku} Filmleri")
            startActivity(intent)

        }

        binding.tvShowAllPopularity.setOnClickListener {
            val movies = viewModel.setMoviesByPopularity()
            val intent = Intent(this,FilteredOrSortedMoviesActivity::class.java)
            intent.putExtra(MOVIES,movies)
            intent.putExtra(MOVIE_LIST_TITLE,"Popular Movies")
            startActivity(intent)
        }

        binding.tvShowAllWowieMax.setOnClickListener {
            val movies = viewModel.setMoviesByProducer()
            val intent = Intent(this,FilteredOrSortedMoviesActivity::class.java)
            intent.putExtra(MOVIES,movies)
            intent.putExtra(MOVIE_LIST_TITLE,"Wowie Max Original")
            startActivity(intent)
        }

        binding.iwSearch.setOnClickListener {
            startActivity(
                Intent(this,SearchActivity::class.java)
            )
        }

        binding.iwMenu.setOnClickListener {


            if(!binding.linearLayoutMenu.isVisible){
                val menu = binding.linearLayoutMenu
                menu.animate().apply{translationY(340f).alpha(1f).duration = 500}.start()
                menu.isVisible = true
                binding.clHeader.setBackgroundColor(getColor(R.color.transparentColor))
            }
            else {
                val menu = binding.linearLayoutMenu
                menu.animate().apply{translationY(-340f).alpha(0.0f).duration = 1000}.start()
                menu.isVisible = false
                binding.clHeader.setBackgroundResource(R.drawable.gradient_top_to_bottom_bg)
            }
        }


        binding.tvMenuItem1.setOnClickListener {
            startActivity(
                Intent(this,ErrorActivity::class.java)
            )
        }
        binding.tvMenuItem2.setOnClickListener {
            startActivity(
                Intent(this,ErrorActivity::class.java)
            )
        }
        binding.tvMenuItem3.setOnClickListener {
            startActivity(
                Intent(this,ErrorActivity::class.java)
            )
        }
        binding.tvMenuItem4.setOnClickListener {
            startActivity(
                Intent(this,ErrorActivity::class.java)
            )
        }

        binding.tvExit.setOnClickListener {
            sharedPreferences = getSharedPreferences(LoginActivity.LOGIN_REMEMBER_ME, Context.MODE_PRIVATE)
            sharedPreferences.edit().putBoolean(LoginActivity.LOGIN_REMEMBER_ME,false).apply()
            val intent = Intent(this@MainActivity,LoginActivity::class.java)
            startActivity(intent)
        }

        binding.btnRandom.setOnClickListener {
            val randomMovie = Database.movies.random()
            val intent = Intent(this@MainActivity, MovieDetailActivity::class.java)
            intent.putExtra(MOVIE, randomMovie)
            startActivity(intent)
        }
    }

}