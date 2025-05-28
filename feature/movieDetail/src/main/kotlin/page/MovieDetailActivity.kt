package page

import TMDBAppTheme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import constant.CoreConstant.MOVIE_ID
import dagger.hilt.android.AndroidEntryPoint
import model.MovieDetail
import uistate.AppUiState

@AndroidEntryPoint
class MovieDetailActivity : ComponentActivity() {

    private val viewModel: MovieDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val movieId = intent.getIntExtra(MOVIE_ID, -1)
        if (movieId != -1) {
            viewModel.getMovieDetail(movieId)
        }

        setContent {
            TMDBAppTheme {
                val movieState by viewModel.uiState.collectAsState()

                when (movieState) {
                    is AppUiState.Loading -> {
                        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                            CircularProgressIndicator()
                        }
                    }

                    is AppUiState.Success -> {
                        val movie = (movieState as AppUiState.Success<MovieDetail>).data
                        MovieDetailScreen(
                            movie = movie,
                            onBackClick = { finish() },
                            onToggleFavorite = { },
                            isFavorite = false
                        )
                    }

                    is AppUiState.Error -> {
                        Text("Error: ${(movieState as AppUiState.Error).message}")
                    }
                }
            }

        }
    }
}
