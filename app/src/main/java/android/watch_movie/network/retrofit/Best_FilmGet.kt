package android.watch_movie.network.retrofit

import android.watch_movie.network.entity.Best_FilmNetworkEntity
import retrofit2.http.GET

interface Best_FilmGet {
    @GET("api/v2.2/films/top?type=TOP_250_BEST_FILMS&page=1")
    suspend fun get():Best_FilmNetworkEntity
}