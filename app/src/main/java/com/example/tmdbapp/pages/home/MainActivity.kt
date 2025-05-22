package com.example.tmdbapp.pages.home

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.tmdbapp.pages.detail.MovieDetailActivity
import com.example.tmdbapp.pages.home.ui.theme.TMDBAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val popularViewModel: PopularViewModel by viewModels()
    private val favoriteViewModel: FavoriteViewModel by viewModels()
    private val ratedViewModel: RatedViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TMDBAppTheme {
                MainTab(
                    onNavigateToDetail = {
                        startActivity(Intent(this, MovieDetailActivity::class.java))
                    },
                    popularViewModel,
                    ratedViewModel,
                    favoriteViewModel
                )
            }
        }
    }
}

@Composable
fun MainTab(
    onNavigateToDetail: () -> Unit,
    popularViewModel: PopularViewModel,
    ratedViewModel: RatedViewModel,
    favoriteViewModel: FavoriteViewModel
) {
    val tabs = listOf("Popular", "Top Rated", "Favorite")
    var selectedTab by remember { mutableStateOf(0) }

    Column(modifier = Modifier.fillMaxSize()) {
        TabRow(selectedTabIndex = selectedTab) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    text = { Text(title) },
                    selected = selectedTab == index,
                    onClick = { selectedTab = index })
            }
        }

        when (selectedTab) {
            0 -> TabScreen(onNavigateToDetail, 1, popularViewModel)
            1 -> TabScreen(onNavigateToDetail, 2, ratedViewModel)
            2 -> TabScreen(onNavigateToDetail, 3, favoriteViewModel)
        }
    }
}
