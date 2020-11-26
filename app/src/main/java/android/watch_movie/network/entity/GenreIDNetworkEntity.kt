package android.watch_movie.network.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class GenreIDNetworkEntity(
    @SerializedName("genres")
    @Expose
    val genres: List<GenreNetworkEntity>?

)