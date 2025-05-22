package com.example.tmdbapp.data.api

import com.example.tmdbapp.data.dto.MovieListResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    @GET("/4/account/{account_object_id}/movie/favorites")
    suspend fun getFavoriteMovies(
        @Path("account_object_id") accountObjectId: String, @Query("page") page: Int
    ): MovieListResponse

    @GET("/4/account/{account_object_id}/movie/rated")
    suspend fun getRatedMovies(
        @Path("account_object_id") accountObjectId: String, @Query("page") page: Int
    ): MovieListResponse

    @GET("/4/account/{account_object_id}/movie/recommendations")
    suspend fun getPopularMovies(
        @Path("account_object_id") accountObjectId: String, @Query("page") page: Int
    ): MovieListResponse
}
