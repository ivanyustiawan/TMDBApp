package com.example.tmdbapp.domain.usecase

import com.example.tmdbapp.domain.model.MovieDetail
import com.example.tmdbapp.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetDetailMovieUseCase @Inject constructor(private val repository: MovieRepository) {
    suspend operator fun invoke(movieId: Int): Flow<MovieDetail> =
        repository.getDetailMovie(movieId)
}