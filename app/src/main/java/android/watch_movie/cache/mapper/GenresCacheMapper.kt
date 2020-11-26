package android.watch_movie.cache.mapper

import android.watch_movie.cache.entity.GenresCache
import android.watch_movie.model.Genre
import android.watch_movie.util.EntityMapper
import javax.inject.Inject

class GenresCacheMapper
@Inject constructor() : EntityMapper<GenresCache, Genre> {
    override fun mapFromEntity(entity: GenresCache): Genre {
        return Genre(
            idGenre = entity.idGenre,
            genre = entity.genre
        )
    }

    override fun mapToEntity(domainModel: Genre): GenresCache {
        return GenresCache(
            idGenre = domainModel.idGenre,
            genre = domainModel.genre
        )
    }

    fun mapFromEntityList(entities: List<GenresCache>): List<Genre> {
        return entities.map { mapFromEntity(it) }
    }
}