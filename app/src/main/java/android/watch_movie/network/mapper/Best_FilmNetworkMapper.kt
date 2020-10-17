package android.watch_movie.network.mapper

import android.watch_movie.model.Best_Film
import android.watch_movie.network.entity.Best_FilmNetworkEntity
import android.watch_movie.util.EntityMapper
import javax.inject.Inject

class Best_FilmNetworkMapper
@Inject
constructor() : EntityMapper<Best_FilmNetworkEntity, Best_Film> {
   lateinit var filmNetworkMapper:FilmNetworkMapper
    override fun mapFromEntity(entity: Best_FilmNetworkEntity): Best_Film {
        return Best_Film(
            pagesCount = entity.pagesCount,
            films = entity.films?.let { filmNetworkMapper.mapFromEntityList(it) }
        )
    }

    override fun mapToEntity(domainModel: Best_Film): Best_FilmNetworkEntity {
        return Best_FilmNetworkEntity(
            pagesCount = domainModel.pagesCount,
            films = domainModel.films?.let { filmNetworkMapper.mapToEntityList(it) }
        )
    }


}