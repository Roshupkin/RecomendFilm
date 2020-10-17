package android.watch_movie.network.mapper

import android.watch_movie.model.Country
import android.watch_movie.model.Genre
import android.watch_movie.network.entity.CountryNetworkEntiy
import android.watch_movie.network.entity.GenreNetworkEntity
import android.watch_movie.util.EntityMapper
import javax.inject.Inject

class GenreNetworkMapper @Inject
constructor() : EntityMapper<GenreNetworkEntity, Genre> {
    override fun mapFromEntity(entity: GenreNetworkEntity): Genre {
        return Genre(
            genre = entity.genre
        )
    }

    override fun mapToEntity(domainModel: Genre): GenreNetworkEntity {
        return GenreNetworkEntity(
            genre = domainModel.genre
        )
    }

    fun mapFromEntityList(entites: List<GenreNetworkEntity>): List<Genre> {
        return entites.map { mapFromEntity(it) }
    }
    fun mapToEntityList(entites: List<Genre>): List<GenreNetworkEntity> {
        return entites.map { mapToEntity(it) }
    }
}