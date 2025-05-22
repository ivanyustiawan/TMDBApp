package com.example.tmdbapp.data.repository

import com.example.tmdbapp.core.network.NetworkConstant
import com.example.tmdbapp.data.api.MovieApi
import com.example.tmdbapp.data.mapper.toModel
import com.example.tmdbapp.domain.model.Movie
import com.example.tmdbapp.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class MovieRepositoryImpl(
    private val movieApi: MovieApi,
) : MovieRepository {

    override suspend fun getPopularMovies(page: Int): Flow<List<Movie>> = flow {
        val response = movieApi.getPopularMovies(page)
        emit(response.results?.map { it.toModel() } ?: emptyList())
    }.catch { e ->
        throw e
    }

    override suspend fun getRatedMovies(page: Int): Flow<List<Movie>> = flow {
        val response = movieApi.getRatedMovies(page)
        emit(response.results?.map { it.toModel() } ?: emptyList())
    }.catch { e ->
        throw e
    }

    override suspend fun getFavoriteMovies(page: Int): Flow<List<Movie>> = flow {
        val response = movieApi.getFavoriteMovies(NetworkConstant.ACCOUNT_OBJECT_ID, page)
        emit(response.results?.map { it.toModel() } ?: emptyList())
    }.catch { e ->
        throw e
    }

}
