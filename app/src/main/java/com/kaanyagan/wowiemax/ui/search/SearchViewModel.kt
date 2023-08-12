package com.kaanyagan.wowiemax.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kaanyagan.wowiemax.data.Database
import com.kaanyagan.wowiemax.data.entity.state.SearchMovieListState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SearchViewModel: ViewModel() {

    private val _searchMovieListState: MutableStateFlow<SearchMovieListState> =
        MutableStateFlow(SearchMovieListState.Idle)
    val searchMovieListState: StateFlow<SearchMovieListState> = _searchMovieListState

    init {
        getSearchMovieList()
    }

    private fun getSearchMovieList() {
        viewModelScope.launch {
            _searchMovieListState.value = SearchMovieListState.Loading
            delay(2000)
            _searchMovieListState.value = SearchMovieListState.Result(Database.movies)
        }
    }
}