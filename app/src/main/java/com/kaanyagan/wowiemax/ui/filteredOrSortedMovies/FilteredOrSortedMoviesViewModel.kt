package com.kaanyagan.wowiemax.ui.filteredOrSortedMovies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kaanyagan.wowiemax.data.entity.model.Movie
import com.kaanyagan.wowiemax.data.entity.state.SpecialMovieListState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FilteredOrSortedMoviesViewModel:ViewModel() {

    private val _specialMovieListState:MutableStateFlow<SpecialMovieListState> = MutableStateFlow(SpecialMovieListState.Idle)
    val specialMovieListState:StateFlow<SpecialMovieListState> = _specialMovieListState

    fun getMovies(movies:ArrayList<Movie>?){
        viewModelScope.launch {
            runCatching {
                if(movies.isNullOrEmpty()) _specialMovieListState.emit(SpecialMovieListState.Empty)
                else {
                    val moviesList = movies.toList()
                    _specialMovieListState.value = SpecialMovieListState.Result(moviesList)}
            }.onFailure {
                _specialMovieListState.value = SpecialMovieListState.Error(it)
            }
        }
    }
}