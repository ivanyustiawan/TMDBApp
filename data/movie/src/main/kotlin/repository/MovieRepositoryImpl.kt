package repository

import com.example.tmdbapp.base.network.constant.NetworkConstant
import api.MovieApi
import mapper.toModel
import movie.model.Movie
import movie.model.MovieDetail
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import movie.repository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
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

    override suspend fun getDetailMovie(movieId: Int): Flow<MovieDetail>  = flow {
        val response = movieApi.getMovieDetail(movieId)
        emit(response.toModel())
    }.catch { e ->
        throw e
    }

}
