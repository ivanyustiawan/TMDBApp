package com.example.tmdbapp.domain.repository

import kotlinx.coroutines.flow.Flow
import com.example.tmdbapp.domain.model.Movie

interface MovieRepository {
    suspend fun getPopularMovies(page: Int) : Flow<List<Movie>>
    suspend fun getRatedMovies(page: Int) : Flow<List<Movie>>
    suspend fun getFavoriteMovies(page: Int) : Flow<List<Movie>>
}