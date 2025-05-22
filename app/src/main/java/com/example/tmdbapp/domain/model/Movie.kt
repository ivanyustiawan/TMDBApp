package com.example.tmdbapp.domain.model

data class Movie(
    val adult: Boolean,
    val backdropPath: String,
    val genreIds: List<Long>,
    val id: Int,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val releaseDate: String,
    val title: String,
    val video: Boolean,
    val voteAverage: Double,
    val voteCount: Long,
)

data class MovieDetail(
    val adult: Boolean,
    val backdropPath: String,
    val posterPath: String,
    val title: String,
    val voteAverage: Double,
    val voteCount: Long,
    val releaseDate: String,
    val originalLanguage: String,
    val overview: String,
)


