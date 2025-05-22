package com.example.tmdbapp.data.mapper

import com.example.tmdbapp.core.network.NetworkConstant.BASE_POSTER_GRID_IMAGE_URL
import com.example.tmdbapp.core.network.orFalse
import com.example.tmdbapp.core.network.orZero
import com.example.tmdbapp.data.dto.MovieDto
import com.example.tmdbapp.domain.model.Movie

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

