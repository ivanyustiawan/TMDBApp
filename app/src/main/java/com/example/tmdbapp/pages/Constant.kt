package com.example.tmdbapp.pages

object Constant {
    const val MOVIE_ID = "MOVIE_ID"

    enum class TabCategory(val label: String) {
        POPULAR("Popular"),
        TOP_RATED("Top Rated"),
        FAVORITE("Favorite"),
    }
}