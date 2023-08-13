package com.kaanyagan.wowiemax.data.entity.state

import com.kaanyagan.wowiemax.data.entity.model.Movie

sealed class SearchMovieListState {
    object Idle : SearchMovieListState()
    object Loading : SearchMovieListState()
    class Result(val movies: List<Movie>) : SearchMovieListState()
    class Error(val throwable: Throwable) : SearchMovieListState()
}