package android.watch_movie.network.api

import android.watch_movie.network.entity.DetaildFilmEntity
import android.watch_movie.network.entity.GenresIDNetworkEntity
import android.watch_movie.network.entity.ListFilmsNetworkEntity
import androidx.lifecycle.LiveData
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface FilmsApi {
    @Headers("accept: application/json", "X-API-KEY: 6a891f6b-51bb-4aec-ab49-667e195f8a8e")
    @GET("api/v2.2/films/top?type=TOP_250_BEST_FILMS&page=1")
    suspend fun getTop250Film(): ListFilmsNetworkEntity

    @Headers("accept: application/json", "X-API-KEY: 6a891f6b-51bb-4aec-ab49-667e195f8a8e")
    @GET("api/v2.1/films/filters")
    suspend fun getIDGenres(): GenresIDNetworkEntity

    @Headers("accept: application/json", "X-API-KEY: 6a891f6b-51bb-4aec-ab49-667e195f8a8e")
    @GET("api/v2.1/films/search-by-filters?")
    suspend fun getFilmByFilters(
        @Query("genre")
        genre: Int? = 12,
        @Query("order")
        order: String,
        @Query("type")
        type: String,
        @Query("ratingFrom")
        ratingFrom: Int,
        @Query("ratingTo")
        ratingTo: Int,
        @Query("yearFrom")
        yearFrom: Int,
        @Query("yearTo")
        yearTo: Int,
        @Query("page")
        page: Int,
    ): ListFilmsNetworkEntity


    @Headers("accept: application/json", "X-API-KEY: 6a891f6b-51bb-4aec-ab49-667e195f8a8e")
    @GET("api/v2.1/films/{filmID}?append_to_response=BUDGET&append_to_response=RATING&append_to_response=REVIEW")
    suspend fun getAllInfoFilm(
        @Path("filmID")
        filmId: Int?,
    ): DetaildFilmEntity

    @Headers("accept: application/json", "X-API-KEY: 6a891f6b-51bb-4aec-ab49-667e195f8a8e")
    @GET("api/v2.1/films/{filmID}?append_to_response=")
    suspend fun getFilmByID(
        @Path("filmID")
        filmId: Int?,
    ): DetaildFilmEntity
}
