package movie.usecase

import kotlinx.coroutines.flow.Flow
import movie.model.Movie
import movie.repository.MovieRepository
import javax.inject.Inject

class GetRatedMoviesUseCase @Inject constructor (private val repository: MovieRepository) {
    suspend operator fun invoke(page: Int): Flow<List<Movie>> = repository.getRatedMovies(page)
}
