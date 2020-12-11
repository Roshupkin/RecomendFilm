package android.watch_movie.cache.mapper

import android.watch_movie.cache.entity.GenresIDCache
import android.watch_movie.model.Genre
import android.watch_movie.util.EntityMapper
import javax.inject.Inject

class GenresCacheMapper
@Inject constructor() : EntityMapper<GenresIDCache, Genre> {
    override fun mapFromEntity(entity: GenresIDCache): Genre {
        return Genre(
            idGenre = entity.idGenre,
            genre = entity.genre
        )
    }

    override fun mapToEntity(domainModel: Genre): GenresIDCache {
        return GenresIDCache(
            idGenre = domainModel.idGenre,
            genre = domainModel.genre
        )
    }

    fun mapFromEntityList(entities: List<GenresIDCache>): List<Genre> {
        return entities.map { mapFromEntity(it) }
    }
}