package android.watch_movie.network.api

import android.watch_movie.network.entity.Best_FilmNetworkEntity
import retrofit2.http.GET
import retrofit2.http.Headers

interface Best_FilmGet {
    @Headers(
        "accept: application/json", "X-API-KEY: 6a891f6b-51bb-4aec-ab49-667e195f8a8e"
    )
    @GET("api/v2.2/films/top?type=TOP_250_BEST_FILMS&page=1")
    suspend fun get(): Best_FilmNetworkEntity
}