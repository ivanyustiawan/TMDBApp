package page

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import constant.CommonConstant
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import model.Movie
import uistate.AppUiState
import usecase.GetFavoriteMoviesUseCase
import usecase.GetPopularMoviesUseCase
import usecase.GetTopRatedMoviesUseCase
import javax.inject.Inject

@HiltViewModel
class TabViewModel @Inject constructor(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val getTopRatedMoviesUseCase: GetTopRatedMoviesUseCase,
    private val getFavoriteMoviesUseCase: GetFavoriteMoviesUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow<AppUiState<List<Movie>>>(AppUiState.Loading)
    val uiState = _uiState.asStateFlow()

    private var isLoading = false
    private var currentPage = 1
    private var currentData = mutableListOf<Movie>()

    fun getData(reset: Boolean = false, tab: CommonConstant.TabCategory) {
        if (isLoading) return
        isLoading = true

        if (reset) {
            currentPage = 1
            currentData.clear()
            _uiState.value = AppUiState.Success(emptyList())
        }

        viewModelScope.launch {
            when (tab) {
                CommonConstant.TabCategory.POPULAR -> getPopularMovies()
                CommonConstant.TabCategory.TOP_RATED -> getTopRatedMovies()
                else -> getFavoriteMovies()
            }
        }
    }

    private suspend fun getPopularMovies() {
        getPopularMoviesUseCase(currentPage)
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

    private suspend fun getTopRatedMovies() {
        getTopRatedMoviesUseCase(currentPage)
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

    private suspend fun getFavoriteMovies() {
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
