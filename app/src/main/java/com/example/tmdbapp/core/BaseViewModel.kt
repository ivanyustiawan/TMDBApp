package com.example.tmdbapp.core

import androidx.lifecycle.ViewModel
import com.example.tmdbapp.domain.model.Movie
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class BaseViewModel : ViewModel() {
    protected val _uiState = MutableStateFlow<AppUiState<List<Movie>>>(AppUiState.Loading)
    val uiState = _uiState.asStateFlow()
    open fun getMovies(event: Boolean = false) {}
}