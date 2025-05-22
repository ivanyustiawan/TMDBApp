
import app.cash.turbine.test
import repository.MovieRepository
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
import usecase.GetFavoriteMoviesUseCase

@ExperimentalCoroutinesApi
class GetFavoriteMoviesUseCaseTest {

    private lateinit var movieRepository: MovieRepository
    private lateinit var getFavoriteMoviesUseCase: GetFavoriteMoviesUseCase

    @Before
    fun setUp() {
        movieRepository = mockk()
        getFavoriteMoviesUseCase = GetFavoriteMoviesUseCase(movieRepository)
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `test getFavoriteMovies success`() = runTest {
        val expectedResult = mockListofMovieSuccess

        coEvery {
            movieRepository.getFavoriteMovies(1)
        } returns flowOf(expectedResult)

        getFavoriteMoviesUseCase(1).test {
            assertThat(awaitItem()).isEqualTo(expectedResult)
            awaitComplete()
        }
    }

    @Test
    fun `test getFavoriteMovies success empty`() = runTest {
        val expectedResult = listOf<Movie>()

        coEvery {
            movieRepository.getFavoriteMovies(1)
        } returns flowOf(expectedResult)

        getFavoriteMoviesUseCase(1).test {
            assertThat(awaitItem()).isEqualTo(expectedResult)
            awaitComplete()
        }
    }

    @Test
    fun `test getFavoriteMovies error`() = runTest {
        val exception = Exception("error")

        coEvery {
            movieRepository.getFavoriteMovies(1)
        } returns flow {
            throw exception
        }

        getFavoriteMoviesUseCase(1).test {
            val thrown = awaitError()
            assertThat(thrown.message).isEqualTo("error")
        }
    }

}