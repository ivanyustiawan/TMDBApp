
import app.cash.turbine.test
import movie.repository.MovieRepository
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.unmockkAll
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import movie.model.Movie
import movie.usecase.GetRatedMoviesUseCase

@ExperimentalCoroutinesApi
class GetRatedMoviesUseCaseUseCaseTest {

    private lateinit var movieRepository: MovieRepository
    private lateinit var getRatedMoviesUseCase: GetRatedMoviesUseCase

    @Before
    fun setUp() {
        movieRepository = mockk()
        getRatedMoviesUseCase = GetRatedMoviesUseCase(movieRepository)
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `test getRatedMovies success`() = runTest {
        val expectedResult = mockListofMovieSuccess

        coEvery {
            movieRepository.getRatedMovies(1)
        } returns flowOf(expectedResult)

        getRatedMoviesUseCase(1).test {
            assertThat(awaitItem()).isEqualTo(expectedResult)
            awaitComplete()
        }
    }

    @Test
    fun `test getRatedMovies success empty`() = runTest {
        val expectedResult = listOf<Movie>()

        coEvery {
            movieRepository.getRatedMovies(1)
        } returns flowOf(expectedResult)

        getRatedMoviesUseCase(1).test {
            assertThat(awaitItem()).isEqualTo(expectedResult)
            awaitComplete()
        }
    }

    @Test
    fun `test getRatedMovies error`() = runTest {
        val exception = Exception("error")

        coEvery {
            movieRepository.getRatedMovies(1)
        } returns flow {
            throw exception
        }

        getRatedMoviesUseCase(1).test {
            val thrown = awaitError()
            assertThat(thrown.message).isEqualTo("error")
        }
    }

}