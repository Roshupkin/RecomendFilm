package android.watch_movie.model


import android.util.Log
import android.watch_movie.network.entity.CountryNetworkEntity
import android.watch_movie.network.entity.GenreNetworkEntity


data class Film(
    var filmId: Int,
    var nameRu: String? = null,
    var nameEn: String? = null,
    var year: String? = null,
    var type: String? = null,
    var filmLength: String? = null,
    var countries: List<CountryNetworkEntity>? = null,
    var genres: List<GenreNetworkEntity>? = null,
    var rating: String? = null,
    var ratingVoteCount: Int? = null,
    var posterUrl: String? = null,
    var posterUrlPreview: String? = null,
    var ratingChange: Any? = null
)/*{
    init {
        val TAG = "Film"
        if(nameEn == null)Log.e(TAG,"ERROR nameEn: $filmId $nameRu $nameEn")
        if(ratingChange == null)Log.e(TAG,"ERROR ratingChange: $filmId $nameRu $ratingChange")
        if(countries == null)Log.e(TAG,"ERROR countries: $filmId $nameRu $countries")
        if(genres == null)Log.e(TAG,"ERROR genres: $filmId $nameRu $genres")




    }
}*/