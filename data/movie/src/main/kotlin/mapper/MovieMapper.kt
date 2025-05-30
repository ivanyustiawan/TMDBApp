package mapper

import com.example.tmdbapp.core.network.BuildConfig
import constant.MovieConstant.GRID_IMAGE_SIZE
import constant.MovieConstant.POSTER_BACKDROP_IMAGE_SIZE
import constant.MovieConstant.POSTER_DETAIL_IMAGE_SIZE
import dto.MovieDetailDto
import dto.MovieDto
import extension.orFalse
import extension.orZero
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
    posterPath = BuildConfig.BASE_IMAGE_URL + GRID_IMAGE_SIZE + posterPath.orEmpty(),
    releaseDate = releaseDate.orEmpty(),
    title = title.orEmpty(),
    video = video.orFalse(),
    voteAverage = voteAverage.orZero(),
    voteCount = voteCount.orZero(),
)

fun MovieDetailDto.toModel(): MovieDetail = MovieDetail(
    adult = adult.orFalse(),
    backdropPath = BuildConfig.BASE_IMAGE_URL + POSTER_BACKDROP_IMAGE_SIZE + backdropPath.orEmpty(),
    posterPath = BuildConfig.BASE_IMAGE_URL + POSTER_DETAIL_IMAGE_SIZE + posterPath.orEmpty(),
    title = title.orEmpty(),
    voteAverage = voteAverage.orZero(),
    voteCount = voteCount.orZero(),
    releaseDate = releaseDate.orEmpty(),
    originalLanguage = originalLanguage.orEmpty(),
    overview = overview.orEmpty(),
)

