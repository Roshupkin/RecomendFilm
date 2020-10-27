package android.watch_movie.network.mapper

import android.watch_movie.model.ListFilms
import android.watch_movie.network.entity.ListFilmsNetworkEntity
import android.watch_movie.util.EntityMapper
import javax.inject.Inject

class ListFilmsNetworkMapper
@Inject constructor(
    private val filmNetworkMapper: FilmNetworkMapper
) : EntityMapper<ListFilmsNetworkEntity, ListFilms> {
    override fun mapFromEntity(entity: ListFilmsNetworkEntity): ListFilms {


        return ListFilms(
            pagesCount = entity.pagesCount,
            films = entity.films?.let { filmNetworkMapper.mapFromEntityList(it) }
        )
    }

    override fun mapToEntity(domainModel: ListFilms): ListFilmsNetworkEntity {
        return ListFilmsNetworkEntity(
            pagesCount = domainModel.pagesCount,
            films = domainModel.films?.let { filmNetworkMapper.mapToEntityList(it) }
        )
    }

    fun mapFromEntityList(entities: List<ListFilmsNetworkEntity>): List<ListFilms> {
        return entities.map { mapFromEntity(it) }
    }
}