package com.example.tmdbapp.pages

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tmdbapp.core.AppUiState
import com.example.tmdbapp.domain.model.Movie
import com.example.tmdbapp.domain.usecase.GetPopularMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PopularViewModel @Inject constructor(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<AppUiState<List<Movie>>>(AppUiState.Loading)
    val uiState = _uiState.asStateFlow()

    private fun getMovies() {
        viewModelScope.launch {
            getPopularMoviesUseCase(1)
                .onStart {
                    _uiState.value = AppUiState.Loading
                }
                .catch { e ->
                    _uiState.value = AppUiState.Error(e.localizedMessage ?: "Unexpected Error")
                }
                .collect { movies ->
                    _uiState.value = AppUiState.Success(movies)
                }
        }
    }
}