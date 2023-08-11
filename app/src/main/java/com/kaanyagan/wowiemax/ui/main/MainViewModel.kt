package com.kaanyagan.wowiemax.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kaanyagan.wowiemax.data.Database
import com.kaanyagan.wowiemax.data.state.MovieListState
import com.kaanyagan.wowiemax.data.entity.model.Categorie
import com.kaanyagan.wowiemax.data.entity.state.HeaderSlideMovieState
import com.kaanyagan.wowiemax.data.entity.state.SlideMovieState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class MainViewModel : ViewModel() {


    private val _movieListByCategoryState:MutableStateFlow<MovieListState> = MutableStateFlow(MovieListState.Idle)
    val movieListByCategoryState:StateFlow<MovieListState> = _movieListByCategoryState

    private val _movieListByPopularityState:MutableStateFlow<MovieListState> = MutableStateFlow(MovieListState.Idle)
    val movieListByPopularityState:StateFlow<MovieListState> = _movieListByPopularityState


    private val _movieListByStreamerState:MutableStateFlow<MovieListState> = MutableStateFlow(MovieListState.Idle)
    val movieListByStreamerState:StateFlow<MovieListState> = _movieListByStreamerState

    private val _slideMovieState:MutableStateFlow<SlideMovieState> = MutableStateFlow(
        SlideMovieState.Idle)
    val slideMovieState:StateFlow<SlideMovieState> = _slideMovieState

    private val _headerSlideMovieState:MutableStateFlow<HeaderSlideMovieState> = MutableStateFlow(
        HeaderSlideMovieState.Idle)
    val headerSlideMovieState:StateFlow<HeaderSlideMovieState> = _headerSlideMovieState



    fun getMoviesByCategory(category: Categorie){

        viewModelScope.launch {
            _movieListByCategoryState.value = MovieListState.Loading
            delay(1000)
            val movies = Database.movies.filter { it.categories.contains(category) }

            kotlin.runCatching {
                if (!movies.isNullOrEmpty())
                    _movieListByCategoryState.value = MovieListState.Success(movies)

            else{
                    _movieListByCategoryState.value = MovieListState.isEmpty
             }
            }.onFailure {
                _movieListByCategoryState.value = MovieListState.Error(it)
            }
        }
        }

    fun getMoviesByPopularity(){

        viewModelScope.launch {
            _movieListByPopularityState.value = MovieListState.Loading
            delay(1000)
            val movies = Database.movies.sortedBy { it.rateOfLike }.reversed()


            kotlin.runCatching {
                if (!movies.isNullOrEmpty())
                    _movieListByPopularityState.value = MovieListState.Success(movies)

                else{
                    _movieListByPopularityState.value = MovieListState.isEmpty
                }
            }.onFailure {
                _movieListByPopularityState.value = MovieListState.Error(it)
            }
        }
    }


    fun getMoviesByStreamer(producer :String){

        viewModelScope.launch {
            _movieListByPopularityState.value = MovieListState.Loading
            delay(1000)
            val movies = Database.movies.filter { it.producer == producer }


            kotlin.runCatching {
                if (!movies.isNullOrEmpty())
                    _movieListByStreamerState.value = MovieListState.Success(movies)

                else{
                    _movieListByStreamerState.value = MovieListState.isEmpty
                }
            }.onFailure {
                _movieListByStreamerState.value = MovieListState.Error(it)
            }
        }
    }

    fun getHeaderSlideMovie(producer:String){
        viewModelScope.launch {
            _headerSlideMovieState.value = HeaderSlideMovieState.Loading
            delay(1000)
            val movies = Database.movies.filter { it.producer == producer }
            runCatching {
                if (!movies.isNullOrEmpty())
                    _headerSlideMovieState.value = HeaderSlideMovieState.Success(movies)
                else
                    _headerSlideMovieState.value = HeaderSlideMovieState.IsEmpty
            }.onFailure {
                _headerSlideMovieState.value = HeaderSlideMovieState.Error(it)
            }
        }
    }

    fun getSlideMovie(producer:String){
        viewModelScope.launch {
            _slideMovieState.value = SlideMovieState.Loading
            delay(1000)
            val movies = Database.movies.filter { it.producer == producer }
            runCatching {
                if (!movies.isNullOrEmpty())
                    _slideMovieState.value = SlideMovieState.Success(movies)
                else
                    _slideMovieState.value = SlideMovieState.IsEmpty
            }.onFailure {
                _slideMovieState.value = SlideMovieState.Error(it)
            }
        }
    }
}
