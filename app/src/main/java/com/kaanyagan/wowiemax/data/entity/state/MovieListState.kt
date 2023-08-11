package com.kaanyagan.wowiemax.data.state

import com.kaanyagan.wowiemax.data.entity.model.Movie

sealed class MovieListState{

        object Idle: MovieListState()
        object Loading: MovieListState()
        object isEmpty:MovieListState()
        class Success(val movies: List<Movie>): MovieListState()
        class Error(throwable: Throwable): MovieListState()
}

