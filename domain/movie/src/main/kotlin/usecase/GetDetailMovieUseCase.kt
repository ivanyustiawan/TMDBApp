package movie.usecase

import movie.model.MovieDetail
import repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetDetailMovieUseCase @Inject constructor(private val repository: MovieRepository) {
    suspend operator fun invoke(movieId: Int): Flow<MovieDetail> =
        repository.getDetailMovie(movieId)
}