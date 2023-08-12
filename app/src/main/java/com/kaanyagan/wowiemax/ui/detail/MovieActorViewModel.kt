package com.kaanyagan.wowiemax.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kaanyagan.wowiemax.data.Database
import com.kaanyagan.wowiemax.data.entity.state.MovieActorState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MovieActorViewModel : ViewModel() {

    private var _movieActorState: MutableStateFlow<MovieActorState> =
        MutableStateFlow(MovieActorState.Idle)
    var movieActorState: StateFlow<MovieActorState> = _movieActorState

    init {
        getMovieActor()
    }

    private fun getMovieActor() {
        viewModelScope.launch {
            kotlin.runCatching {
                _movieActorState.value = MovieActorState.Loading
                delay(1000)
                _movieActorState.value = MovieActorState.Result(Database.actors)
            }.onFailure {
                _movieActorState.value = MovieActorState.Error(it)
            }
        }
    }
}