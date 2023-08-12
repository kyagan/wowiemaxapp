package com.kaanyagan.wowiemax.data.entity.state

import com.kaanyagan.wowiemax.data.entity.model.Movie

sealed class SpecialMovieListState{
    object Idle:SpecialMovieListState()
    object Empty:SpecialMovieListState()
    class Result(val movies:List<Movie>):SpecialMovieListState()
    class Error(val throwable: Throwable):SpecialMovieListState()
}
