package api

import dto.MovieDetailDto
import dto.MovieListResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    @GET("/4/account/{account_object_id}/movie/favorites")
    suspend fun getFavoriteMovies(
        @Path("account_object_id") accountObjectId: String, @Query("page") page: Int
    ): MovieListResponse

    @GET("/3/movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query("page") page: Int
    ): MovieListResponse

    @GET("/3/movie/popular")
    suspend fun getPopularMovies(
        @Query("page") page: Int
    ): MovieListResponse

    @GET("/3/movie/{movie_id}")
    suspend fun getMovieDetail(
        @Path("movie_id") movieId: Int,
    ): MovieDetailDto

}
