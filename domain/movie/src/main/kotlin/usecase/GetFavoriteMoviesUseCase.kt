package usecase

import kotlinx.coroutines.flow.Flow
import movie.model.Movie
import repository.MovieRepository
import javax.inject.Inject

class GetFavoriteMoviesUseCase @Inject constructor (private val repository: MovieRepository) {
    suspend operator fun invoke(page: Int): Flow<List<Movie>> = repository.getFavoriteMovies(page)
}