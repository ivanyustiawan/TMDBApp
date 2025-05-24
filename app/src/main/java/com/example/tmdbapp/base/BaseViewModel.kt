package com.example.tmdbapp.base

import AppUiState
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class BaseViewModel<T> : ViewModel() {
    protected val _uiState = MutableStateFlow<AppUiState<T>>(AppUiState.Loading)
    val uiState = _uiState.asStateFlow()

    open fun getData(param: Boolean = false) {}
}