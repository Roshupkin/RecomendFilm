package android.watch_movie.cache.mapper

import android.watch_movie.cache.entity.GenreCache
import android.watch_movie.cache.entity.GenresCache
import android.watch_movie.model.Genre
import android.watch_movie.util.EntityMapper
import javax.inject.Inject

class GenreCacheMapper
@Inject constructor() : EntityMapper<GenreCache, Genre> {
    override fun mapFromEntity(entity: GenreCache): Genre {
        return Genre(
            idGenre = entity.idGenre,
            genre = entity.genre
        )
    }

    override fun mapToEntity(domainModel: Genre): GenreCache {
        return GenreCache(
            idGenre = domainModel.idGenre,
            genre = domainModel.genre
        )
    }

    fun mapFromEntityList(entities: List<GenreCache>): List<Genre> {
        return entities.map { mapFromEntity(it) }
    }
    fun mapToEntityList(domainModel: List<Genre>):List<GenreCache>{
        return domainModel.map{mapToEntity(it)}
    }
}