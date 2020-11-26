package android.watch_movie.model


import android.watch_movie.network.entity.CountryNetworkEntity
import android.watch_movie.network.entity.GenreNetworkEntity


data class Film(
    var filmId: Int,
    var countFilm: Int? = 0,
    var nameRu: String? = null,
    var nameEn: String? = null,
    var webUrl: String?=null,
    var posterUrl: String? = null,
    var posterUrlPreview: String? = null,
    var year: String? = null,
    var filmLength: String? = null,
    var slogan:String? = null,
    var description:String?= null,
    var type: String? = null,
    var ratingMpa:String? = null,
    var ratingAgeLimits:Int?=null,
    var premiereRu:String? = null,
    var distributors:String? = null,
    var premiereWorld:String? =null,
    var premiereDigital:String?=null,
    var premiereWorldCountry:String? = null,
    var premiereDvd:String? = null,
    var premiereBluRay:String? = null,
    var distributorRelease:String? = null,
    var countries: List<CountryNetworkEntity>? = null,
    var genres: List<GenreNetworkEntity>? = null,
    var facts:List<String>? = null,
    var seasons:List<Int>? = null,
    var rating: String? = null,
    var ratingVoteCount: Int? = null,
    var ratingChange: Any? = null,
)