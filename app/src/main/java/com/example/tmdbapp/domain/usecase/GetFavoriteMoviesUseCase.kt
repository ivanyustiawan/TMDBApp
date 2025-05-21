package com.example.tmdbapp.domain.usecase

import kotlinx.coroutines.flow.Flow
import com.example.tmdbapp.domain.model.Movie
import com.example.tmdbapp.domain.repository.MovieRepository
import javax.inject.Inject

class GetFavoriteMoviesUseCase @Inject constructor (private val repository: MovieRepository) {
    suspend operator fun invoke(page: Int): Flow<List<Movie>> = repository.getFavoriteMovies(page)
}