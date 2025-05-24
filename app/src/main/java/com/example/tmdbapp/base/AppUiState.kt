package com.example.tmdbapp.base

sealed class AppUiState<out T> {
    data object Loading : AppUiState<Nothing>()
    data class Success<out T>(val data: T) : AppUiState<T>()
    data class Error(val message: String) : AppUiState<Nothing>()
}
