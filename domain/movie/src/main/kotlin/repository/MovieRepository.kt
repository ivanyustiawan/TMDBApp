package repository

import kotlinx.coroutines.flow.Flow
import movie.model.Movie
import movie.model.MovieDetail

interface MovieRepository {
    suspend fun getPopularMovies(page: Int): Flow<List<Movie>>
    suspend fun getTopRatedMovies(page: Int): Flow<List<Movie>>
    suspend fun getFavoriteMovies(page: Int): Flow<List<Movie>>
    suspend fun getDetailMovie(movieId: Int): Flow<MovieDetail>
}