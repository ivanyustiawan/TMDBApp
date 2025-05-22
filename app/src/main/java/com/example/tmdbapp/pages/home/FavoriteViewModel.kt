package com.example.tmdbapp.pages.home

import androidx.lifecycle.viewModelScope
import com.example.tmdbapp.core.AppUiState
import com.example.tmdbapp.core.BaseViewModel
import com.example.tmdbapp.domain.model.Movie
import com.example.tmdbapp.domain.usecase.GetFavoriteMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val getFavoriteMoviesUseCase: GetFavoriteMoviesUseCase
) : BaseViewModel() {

    private var isLoading = false
    private var currentPage = 1
    private var currentData = mutableListOf<Movie>()

    override fun getMovies(reset: Boolean) {
        if (isLoading) return
        isLoading = true

        if (reset) {
            currentPage = 1
            currentData.clear()
            _uiState.value = AppUiState.Success(emptyList())
        }

        viewModelScope.launch {
            getFavoriteMoviesUseCase(currentPage)
                .onStart {
                    _uiState.value = AppUiState.Loading
                }
                .catch { e ->
                    _uiState.value = AppUiState.Error(e.localizedMessage ?: "Unexpected Error")
                    isLoading = false
                }
                .collect { movies ->
                    currentPage++
                    currentData.addAll(movies)
                    _uiState.value = AppUiState.Success(currentData)
                    isLoading = false
                }

        }


    }
}