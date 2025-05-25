package com.example.tmdbapp.pages.detail

import uistate.AppUiState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import movie.model.MovieDetail
import usecase.GetDetailMovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val getDetailMovieUseCase: GetDetailMovieUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<AppUiState<MovieDetail>>(AppUiState.Loading)
    val uiState = _uiState.asStateFlow()

    private var isLoading = false

    fun getMovieDetail(movieId: Int) {
        if (isLoading) return
        isLoading = true

        viewModelScope.launch {
            getDetailMovieUseCase(movieId)
                .onStart {
                    _uiState.value = AppUiState.Loading
                }
                .catch { e ->
                    _uiState.value = AppUiState.Error(e.localizedMessage ?: "Unexpected Error")
                    isLoading = false
                }
                .collect { movieDetail ->
                    _uiState.value = AppUiState.Success(movieDetail)
                    isLoading = false
                }
        }
    }
}