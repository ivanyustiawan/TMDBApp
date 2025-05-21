package com.example.tmdbapp.data.mapper

import com.example.tmdbapp.data.dto.MovieDto
import com.example.tmdbapp.domain.model.Movie

fun MovieDto.toModel(): Movie = Movie(
    adult = adult,
    backdropPath = backdropPath,
    genreIds = genreIds,
    id = id,
    originalLanguage = originalLanguage,
    originalTitle = originalTitle,
    overview = overview,
    popularity = popularity,
    posterPath = posterPath,
    releaseDate = releaseDate,
    title = title,
    video = video,
    voteAverage = voteAverage,
    voteCount = voteCount,
)

