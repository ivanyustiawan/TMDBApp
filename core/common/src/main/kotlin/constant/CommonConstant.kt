package constant

object CommonConstant {
    const val MOVIE_ID = "MOVIE_ID"

    enum class TabCategory(val label: String) {
        POPULAR("Popular"),
        TOP_RATED("Top Rated"),
        FAVORITE("Favorite"),
    }
}