package android.watch_movie.network.mapper

import android.watch_movie.network.entity.GenreNetworkEntity
import android.watch_movie.util.EntityMapper
import javax.inject.Inject

class GenreNetworkMapper

@Inject constructor() : EntityMapper<GenreNetworkEntity, String> {
    override fun mapFromEntity(entity: GenreNetworkEntity): String {
        return entity.genre.toString()

    }

    override fun mapToEntity(domainModel: String): GenreNetworkEntity {
        return GenreNetworkEntity(
            genre = domainModel
        )
    }

    fun mapFromEntityList(entities: List<GenreNetworkEntity>): List<String> {
        return entities.map { mapFromEntity(it) }
    }

    fun mapToEntityList(domainModels: List<String>): List<GenreNetworkEntity> {
        return domainModels.map { mapToEntity(it) }
    }

}