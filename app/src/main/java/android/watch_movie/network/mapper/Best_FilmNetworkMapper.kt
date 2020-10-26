package android.watch_movie.network.mapper

import android.watch_movie.model.Best_Film
import android.watch_movie.network.entity.Best_FilmNetworkEntity
import android.watch_movie.util.EntityMapper
import javax.inject.Inject

class Best_FilmNetworkMapper
@Inject constructor(
    private val filmNetworkMapper: FilmNetworkMapper
) : EntityMapper<Best_FilmNetworkEntity, Best_Film> {
    override fun mapFromEntity(entity: Best_FilmNetworkEntity): Best_Film {
       /* val TAG = "Best_FilmNetworkMapper"
        Log.e(TAG, "Hey look! bestFilm ${entity.films}")*/

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

    fun mapFromEntityList(entities: List<Best_FilmNetworkEntity>): List<Best_Film> {
        return entities.map { mapFromEntity(it) }
    }
}