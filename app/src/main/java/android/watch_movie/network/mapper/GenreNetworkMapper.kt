package android.watch_movie.network.mapper

import android.watch_movie.model.Genre
import android.watch_movie.network.entity.GenreNetworkEntity
import android.watch_movie.util.EntityMapper
import javax.inject.Inject

class GenreNetworkMapper
@Inject constructor() : EntityMapper<GenreNetworkEntity, Genre> {
    override fun mapFromEntity(entity: GenreNetworkEntity): Genre {
        return Genre(
            idGenre = entity.idGenre,
            genre = entity.genre
        )
    }

    override fun mapToEntity(domainModel: Genre): GenreNetworkEntity {
        return GenreNetworkEntity(
            idGenre = domainModel.idGenre,
            genre = domainModel.genre
        )
    }

    fun mapFromEntityList(entities: List<GenreNetworkEntity>): List<Genre> {
        return entities.map { mapFromEntity(it) }
    }

    fun mapToEntityList(domainModels: List<Genre>): List<GenreNetworkEntity> {
        return domainModels.map { mapToEntity(it) }
    }


}