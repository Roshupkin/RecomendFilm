package android.watch_movie.network.mapper

import android.watch_movie.model.FilterGC
import android.watch_movie.network.entity.GenresIDNetworkEntity
import android.watch_movie.util.EntityMapper
import javax.inject.Inject

class GenreIDNetworkMapper
@Inject
constructor(
    private val genrIDNetworkMapper: GenrIDNetworkMapper
):EntityMapper<GenresIDNetworkEntity,FilterGC> {
    override fun mapFromEntity(entity: GenresIDNetworkEntity): FilterGC {
        return FilterGC(
            genres = entity.genres?.let { genrIDNetworkMapper.mapFromEntityList(it) }
        )
    }

    override fun mapToEntity(domainModel: FilterGC): GenresIDNetworkEntity {
       return GenresIDNetworkEntity(
           genres = domainModel.genres?.let { genrIDNetworkMapper.mapToEntityList(it) }

       )
    }
}