package android.watch_movie.db.mapp

import android.watch_movie.db.cacheentity.CountryCacheEntity
import android.watch_movie.db.cacheentity.GenreCacheEntity
import android.watch_movie.model.Country
import android.watch_movie.model.Genre
import android.watch_movie.util.EntityMapper
import javax.inject.Inject

class GenreCacheMapper
@Inject
constructor():EntityMapper<GenreCacheEntity,Genre>{
    override fun mapFromEntity(entity: GenreCacheEntity): Genre {
        return Genre(
            genre = entity.genre
        )
    }

    override fun mapToEntity(domainModel: Genre): GenreCacheEntity {
        return GenreCacheEntity(
            genre = domainModel.genre

        )
    }
    fun mapFromEntityList(entites: List<GenreCacheEntity>): List<Genre> {
        return entites.map { mapFromEntity(it) }
    }
    fun mapToEntityList(entites: List<Genre>): List<GenreCacheEntity> {
        return entites.map { mapToEntity(it) }
    }
}