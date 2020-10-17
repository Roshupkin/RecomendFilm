package android.watch_movie.db.onetomany

import android.watch_movie.db.cacheentity.CountryCacheEntity
import android.watch_movie.db.cacheentity.Film_CacheEntity
import android.watch_movie.db.cacheentity.GenreCacheEntity
import android.watch_movie.model.Country
import android.watch_movie.model.Genre
import androidx.room.Embedded
import androidx.room.Relation

data class FilmWithGenreandCountry(
    var id:Int,
    var filmId: Int,
    var nameRu: String,
    var nameEn: String,
    var year: String,
    var filmLength: String,
    var rating: String,
    var ratingVoteCount: Int,
    var posterUrl: String,
    var posterUrlPreview: String,


    @Relation(
        parentColumn = "id",
        entityColumn = "filmId",
        entity = GenreCacheEntity::class
    )
    var genresEntity: List<GenreCacheEntity>? = null,

    @Relation(
        parentColumn = "id",
        entityColumn = "filmId",
        entity = CountryCacheEntity::class
    )
    var countrysEntity: List<CountryCacheEntity>? = null
)