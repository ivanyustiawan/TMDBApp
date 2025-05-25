package com.example.tmdbapp.pages.home

import AppUiState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.tmdbapp.pages.Constant
import movie.model.Movie

@Composable
fun TabScreen(
    onNavigateToDetail: (Int) -> Unit,
    tab: Constant.TabCategory,
    viewModel: TabViewModel
) {

    val uiState by viewModel.uiState.collectAsState()
    val listState = rememberLazyGridState()

    LaunchedEffect(Unit) {
        viewModel.getData(reset = true, tab = tab)
    }

    LaunchedEffect(listState) {
        snapshotFlow { listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index }
            .collect { lastVisible ->
                if (uiState is AppUiState.Success && lastVisible != null &&
                    lastVisible >= (uiState as AppUiState.Success<List<Movie>>).data.size - 1
                ) {
                    viewModel.getData(tab = tab)
                }
            }
    }


    when (uiState) {
        is AppUiState.Loading -> Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }

        is AppUiState.Success -> {
            val movies = (uiState as AppUiState.Success<List<Movie>>).data
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                state = listState,
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(2.dp)
            ) {
                items(movies) { movie ->
                    Card(
                        modifier = Modifier
                            .padding(2.dp)
                            .fillMaxWidth(),
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                    ) {
                        Box(
                            modifier = Modifier
                                .clickable { onNavigateToDetail(movie.id) }
                                .fillMaxHeight(),
                            contentAlignment = Alignment.Center,
                        ) {
                            AsyncImage(
                                model = movie.posterPath,
                                contentDescription = null,
                                contentScale = ContentScale.FillWidth,
                                modifier = Modifier.fillMaxSize()
                            )
                        }
                    }
                }
            }
        }

        is AppUiState.Error -> {
            val message = (uiState as AppUiState.Error).message
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("Error: $message")
            }
        }
    }

}