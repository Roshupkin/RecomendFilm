package android.watch_movie.network.mapper

import android.watch_movie.model.GenresAndCountresListID
import android.watch_movie.network.entity.GenresIDNetworkEntity
import android.watch_movie.util.EntityMapper
import javax.inject.Inject

class GenresIDNetworkMapper
@Inject
constructor(
    private val genreIDNetworkMapper: GenreIDNetworkMapper
):EntityMapper<GenresIDNetworkEntity,GenresAndCountresListID> {
    override fun mapFromEntity(entity: GenresIDNetworkEntity): GenresAndCountresListID {
        return GenresAndCountresListID(
            genres = entity.genres?.let { genreIDNetworkMapper.mapFromEntityList(it) }
        )
    }

    override fun mapToEntity(domainModel: GenresAndCountresListID): GenresIDNetworkEntity {
       return GenresIDNetworkEntity(
           genres = domainModel.genres?.let { genreIDNetworkMapper.mapToEntityList(it) }

       )
    }
}