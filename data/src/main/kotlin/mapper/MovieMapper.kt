package mapper

import com.example.tmdbapp.core.network.NetworkConstant.BASE_POSTER_BACKDROP_IMAGE_URL
import com.example.tmdbapp.core.network.NetworkConstant.BASE_POSTER_DETAIL_IMAGE_URL
import com.example.tmdbapp.core.network.NetworkConstant.BASE_POSTER_GRID_IMAGE_URL
import com.example.tmdbapp.core.network.orFalse
import com.example.tmdbapp.core.network.orZero
import dto.MovieDetailDto
import dto.MovieDto
import model.Movie
import model.MovieDetail

fun MovieDto.toModel(): Movie = Movie(
    adult = adult.orFalse(),
    backdropPath = backdropPath.orEmpty(),
    genreIds = emptyList(),
    id = id.orZero(),
    originalLanguage = originalLanguage.orEmpty(),
    originalTitle = originalTitle.orEmpty(),
    overview = overview.orEmpty(),
    popularity = popularity.orZero(),
    posterPath = BASE_POSTER_GRID_IMAGE_URL + posterPath.orEmpty(),
    releaseDate = releaseDate.orEmpty(),
    title = title.orEmpty(),
    video = video.orFalse(),
    voteAverage = voteAverage.orZero(),
    voteCount = voteCount.orZero(),
)

fun MovieDetailDto.toModel(): MovieDetail = MovieDetail(
    adult = adult.orFalse(),
    backdropPath = BASE_POSTER_BACKDROP_IMAGE_URL + backdropPath.orEmpty(),
    posterPath = BASE_POSTER_DETAIL_IMAGE_URL + posterPath.orEmpty(),
    title = title.orEmpty(),
    voteAverage = voteAverage.orZero(),
    voteCount = voteCount.orZero(),
    releaseDate = releaseDate.orEmpty(),
    originalLanguage = originalLanguage.orEmpty(),
    overview = overview.orEmpty(),
)

