package com.example.tmdbapp.pages.home

import TMDBAppTheme
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
import constant.CoreConstant.MOVIE_ID
import constant.CoreConstant.TabCategory
import constant.NavigationConstant.ActivityClass.MOVIE_DETAIL_ACTIVITY
import dagger.hilt.android.AndroidEntryPoint
import navigator.ActivityNavigator
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val popularViewModel: TabViewModel by viewModels()
    private val favoriteViewModel: TabViewModel by viewModels()
    private val topRatedViewModel: TabViewModel by viewModels()

    @Inject
    lateinit var activityNavigator: ActivityNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TMDBAppTheme {
                MainTab(
                    onNavigateToDetail = { movieId ->
                        activityNavigator.navigateTo(
                            MOVIE_DETAIL_ACTIVITY.className,
                            mapOf(MOVIE_ID to movieId)
                        )
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
        TabCategory.POPULAR,
        TabCategory.TOP_RATED,
        TabCategory.FAVORITE
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
            0 -> TabScreen(
                onNavigateToDetail,
                TabCategory.POPULAR,
                popularViewModel
            )

            1 -> TabScreen(
                onNavigateToDetail,
                TabCategory.TOP_RATED,
                topRatedViewModel
            )

            2 -> TabScreen(
                onNavigateToDetail,
                TabCategory.FAVORITE,
                favoriteViewModel
            )
        }
    }
}
