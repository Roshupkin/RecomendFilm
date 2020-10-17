package android.watch_movie.network.entity

import android.watch_movie.model.Film

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


data class Best_FilmNetworkEntity(

    @SerializedName("pagesCount")
    @Expose
    var pagesCount: Int,

    @SerializedName("films")
    @Expose
    var films: List<FilmNetworkEntity>? = null
)