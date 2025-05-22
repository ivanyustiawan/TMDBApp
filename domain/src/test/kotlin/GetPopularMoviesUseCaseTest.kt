
import app.cash.turbine.test
import repository.MovieRepository
import usecase.GetPopularMoviesUseCase
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
import model.Movie

@ExperimentalCoroutinesApi
class GetPopularMoviesUseCaseTest {

    private lateinit var movieRepository: MovieRepository
    private lateinit var getPopularMoviesUseCase: GetPopularMoviesUseCase

    @Before
    fun setUp() {
        movieRepository = mockk()
        getPopularMoviesUseCase = GetPopularMoviesUseCase(movieRepository)
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `test getPopularMovies success`() = runTest {
        val expectedResult = mockListofMovieSuccess

        coEvery {
            movieRepository.getPopularMovies(1)
        } returns flowOf(expectedResult)

        getPopularMoviesUseCase(1).test {
            assertThat(awaitItem()).isEqualTo(expectedResult)
            awaitComplete()
        }
    }

    @Test
    fun `test getPopularMovies success empty`() = runTest {
        val expectedResult = listOf<Movie>()

        coEvery {
            movieRepository.getPopularMovies(1)
        } returns flowOf(expectedResult)

        getPopularMoviesUseCase(1).test {
            assertThat(awaitItem()).isEqualTo(expectedResult)
            awaitComplete()
        }
    }

    @Test
    fun `test getPopularMovies error`() = runTest {
        val exception = Exception("error")

        coEvery {
            movieRepository.getPopularMovies(1)
        } returns flow {
            throw exception
        }

        getPopularMoviesUseCase(1).test {
            val thrown = awaitError()
            assertThat(thrown.message).isEqualTo("error")
        }
    }

}