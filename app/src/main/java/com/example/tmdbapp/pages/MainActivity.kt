package com.example.tmdbapp.pages

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import com.example.tmdbapp.pages.ui.theme.TMDBAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val popularViewModel: PopularViewModel by viewModels()
    private val favoriteViewModel: FavoriteViewModel by viewModels()
    private val ratedViewModel: RatedViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TMDBAppTheme {
                MainTab(popularViewModel, ratedViewModel, favoriteViewModel)
            }
        }
    }
}

@Composable
fun MainTab(
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
            0 -> TabScreen(1, popularViewModel)
            1 -> TabScreen(2, ratedViewModel)
            2 -> TabScreen(3, favoriteViewModel)
        }
    }
}
