package android.watch_movie.cache.mapper

import android.watch_movie.cache.entity.GenresCacheEntity
import android.watch_movie.model.Genre
import android.watch_movie.util.EntityMapper
import javax.inject.Inject

class GenresCacheMapper
@Inject constructor() : EntityMapper<GenresCacheEntity, Genre> {
    override fun mapFromEntity(entity: GenresCacheEntity): Genre {
        return Genre(
            idGenre = entity.idGenre,
            genre = entity.genre
        )
    }

    override fun mapToEntity(domainModel: Genre): GenresCacheEntity {
        return GenresCacheEntity(
            idGenre = domainModel.idGenre,
            genre = domainModel.genre
        )
    }

    fun mapFromEntityList(entities: List<GenresCacheEntity>): List<Genre> {
        return entities.map { mapFromEntity(it) }
    }
}