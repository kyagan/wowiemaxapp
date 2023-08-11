package com.kaanyagan.wowiemax.ui.slideMovie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kaanyagan.wowiemax.data.Database
import com.kaanyagan.wowiemax.data.entity.state.SlideMovieState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SlideMovieViewModel: ViewModel() {

    private val _slideMovieState:MutableStateFlow<SlideMovieState> = MutableStateFlow(SlideMovieState.Idle)
    val slideMovieState:StateFlow<SlideMovieState> = _slideMovieState

    fun getSlideMovie(){
        viewModelScope.launch {
            runCatching {
                _slideMovieState.value = SlideMovieState.Loading
                delay(2000)
                _slideMovieState.value = SlideMovieState.Success(Database.movies)
            }.onFailure {
                _slideMovieState.value = SlideMovieState.Error(it)
            }
        }
    }
}