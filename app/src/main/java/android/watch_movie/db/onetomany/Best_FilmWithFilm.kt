package android.watch_movie.db.onetomany

import android.watch_movie.db.cacheentity.Best_FilmCacheEntity
import android.watch_movie.db.cacheentity.Film_CacheEntity
import android.watch_movie.model.Film
import androidx.room.Embedded
import androidx.room.Relation

data class Best_FilmWithFilm (
    var pagesCount: Int,

    @Relation(
        parentColumn = "pagesCount",
        entityColumn = "idPage",entity = Film_CacheEntity::class)
    var filmEntity:List<FilmWithGenreandCountry>? = null
)