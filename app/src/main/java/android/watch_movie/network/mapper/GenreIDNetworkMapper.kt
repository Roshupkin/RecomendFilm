package android.watch_movie.network.mapper

import android.watch_movie.model.Genre
import android.watch_movie.network.entity.GenreIDNetworkEntity
import android.watch_movie.util.EntityMapper
import javax.inject.Inject

class GenreIDNetworkMapper
@Inject constructor() : EntityMapper<GenreIDNetworkEntity, Genre> {
    override fun mapFromEntity(entity: GenreIDNetworkEntity): Genre {
        return Genre(
            idGenre = entity.idGenre,
            genre = entity.genre
        )
    }

    override fun mapToEntity(domainModel: Genre): GenreIDNetworkEntity {
        return GenreIDNetworkEntity(
            idGenre = domainModel.idGenre,
            genre = domainModel.genre
        )
    }

    fun mapFromEntityList(entities: List<GenreIDNetworkEntity>): List<Genre> {
        return entities.map { mapFromEntity(it) }
    }

    fun mapToEntityList(domainModels: List<Genre>): List<GenreIDNetworkEntity> {
        return domainModels.map { mapToEntity(it) }
    }
}