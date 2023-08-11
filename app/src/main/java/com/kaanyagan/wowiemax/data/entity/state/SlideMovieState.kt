package com.kaanyagan.wowiemax.data.entity.state

import com.kaanyagan.wowiemax.data.entity.model.Movie

sealed class SlideMovieState{
    object Idle:SlideMovieState()
    object Loading:SlideMovieState()
    object IsEmpty:SlideMovieState()
    class Success(val movies: List<Movie>) : SlideMovieState()
    class Error(val throwable: Throwable):SlideMovieState()

}
