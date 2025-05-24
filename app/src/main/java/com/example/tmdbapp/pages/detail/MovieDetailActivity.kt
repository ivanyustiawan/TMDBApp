package com.example.tmdbapp.pages.detail

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.tmdbapp.base.AppUiState
import movie.model.MovieDetail
import com.example.tmdbapp.pages.Constant.MOVIE_ID
import com.example.tmdbapp.pages.detail.ui.theme.TMDBAppTheme
import dagger.hilt.android.AndroidEntryPoint

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

                    else -> {}
                }
            }

        }
    }
}

@Composable
fun MovieDetailScreen(
    movie: MovieDetail,
    onBackClick: () -> Unit,
    onToggleFavorite: () -> Unit,
    isFavorite: Boolean
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .height(380.dp)
        ) {
            AsyncImage(
                model = movie.backdropPath,
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .fillMaxWidth()
            )

            IconButton(
                onClick = onBackClick,
                modifier = Modifier
                    .align(Alignment.TopStart)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.White
                )
            }

            Row(
                modifier = Modifier
                    .padding(
                        start = 16.dp,
                        top = 204.dp
                    )
                    .fillMaxWidth()
            ) {
                AsyncImage(
                    model = movie.posterPath,
                    contentDescription = null,
                    modifier = Modifier
                        .background(Color.Red)
                        .width(110.dp)
                        .height(165.dp),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.width(16.dp))

                Column {
                    Spacer(modifier = Modifier.height(24.dp))
                    Text(
                        modifier = Modifier.width(180.dp),
                        text = movie.title,
                        style = MaterialTheme.typography.titleMedium
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = "⭐ ${movie.voteAverage}")
                    Text(text = "👥 ${movie.voteCount} votes")
                    Text(text = "📅 ${movie.releaseDate}")
                    Text(text = "🌐 ${movie.originalLanguage}")
                }
            }


            IconButton(
                onClick = onToggleFavorite,
                modifier = Modifier
                    .padding(end = 8.dp, top = 216.dp)
                    .align(Alignment.TopEnd)
            ) {
                Icon(
                    imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    contentDescription = "Favorite",
                    tint = Color.Red
                )
            }
        }

        Text(
            text = "Overview",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(16.dp)
        )
        Text(
            text = movie.overview,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(horizontal = 16.dp)
        )


    }
}
