package com.kaanyagan.wowiemax.data.entity.state

import com.kaanyagan.wowiemax.data.entity.model.Movie

sealed class HeaderSlideMovieState{
    object Idle:HeaderSlideMovieState()
    object Loading:HeaderSlideMovieState()
    object IsEmpty:HeaderSlideMovieState()
    class Success(val movies: List<Movie>) : HeaderSlideMovieState()
    class Error(val throwable: Throwable):HeaderSlideMovieState()

}
