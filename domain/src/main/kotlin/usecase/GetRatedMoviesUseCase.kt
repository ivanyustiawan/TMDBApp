package usecase

import kotlinx.coroutines.flow.Flow
import model.Movie
import repository.MovieRepository
import javax.inject.Inject

class GetRatedMoviesUseCase @Inject constructor (private val repository: MovieRepository) {
    suspend operator fun invoke(page: Int): Flow<List<Movie>> = repository.getRatedMovies(page)
}
