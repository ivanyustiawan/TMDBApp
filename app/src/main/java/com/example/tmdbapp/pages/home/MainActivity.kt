package com.example.tmdbapp.pages.home

import TMDBAppTheme
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
import com.example.tmdbapp.pages.Constant
import com.example.tmdbapp.pages.Constant.MOVIE_ID
import com.example.tmdbapp.pages.detail.MovieDetailActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val popularViewModel: TabViewModel by viewModels()
    private val favoriteViewModel: TabViewModel by viewModels()
    private val topRatedViewModel: TabViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TMDBAppTheme {
                MainTab(
                    onNavigateToDetail = { movieId ->
                        val intent = Intent(this, MovieDetailActivity::class.java)
                        intent.putExtra(MOVIE_ID, movieId)
                        startActivity(intent)
                    },
                    popularViewModel,
                    topRatedViewModel,
                    favoriteViewModel
                )
            }
        }
    }
}

@Composable
fun MainTab(
    onNavigateToDetail: (Int) -> Unit,
    popularViewModel: TabViewModel,
    topRatedViewModel: TabViewModel,
    favoriteViewModel: TabViewModel
) {
    val tabs = listOf(
        Constant.TabCategory.POPULAR,
        Constant.TabCategory.TOP_RATED,
        Constant.TabCategory.FAVORITE
    )
    var selectedTab by remember { mutableStateOf(0) }

    Column(modifier = Modifier.fillMaxSize()) {
        TabRow(selectedTabIndex = selectedTab) {
            tabs.forEachIndexed { index, tab ->
                Tab(
                    text = { Text(tab.label) },
                    selected = selectedTab == index,
                    onClick = { selectedTab = index })
            }
        }

        when (selectedTab) {
            0 -> TabScreen(onNavigateToDetail, Constant.TabCategory.POPULAR, popularViewModel)
            1 -> TabScreen(onNavigateToDetail, Constant.TabCategory.TOP_RATED, topRatedViewModel)
            2 -> TabScreen(onNavigateToDetail, Constant.TabCategory.FAVORITE, favoriteViewModel)
        }
    }
}
