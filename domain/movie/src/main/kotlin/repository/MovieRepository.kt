package repository

import kotlinx.coroutines.flow.Flow
import model.Movie
import model.MovieDetail

interface MovieRepository {
    suspend fun getPopularMovies(page: Int): Flow<List<Movie>>
    suspend fun getTopRatedMovies(page: Int): Flow<List<Movie>>
    suspend fun getFavoriteMovies(page: Int): Flow<List<Movie>>
    suspend fun getDetailMovie(movieId: Int): Flow<MovieDetail>
}