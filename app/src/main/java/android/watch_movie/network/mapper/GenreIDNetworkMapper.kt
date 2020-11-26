package android.watch_movie.network.mapper

import android.watch_movie.model.FilterGC
import android.watch_movie.network.entity.GenreIDNetworkEntity
import android.watch_movie.util.EntityMapper
import javax.inject.Inject

class GenreIDNetworkMapper
@Inject
constructor(
    private val genreNetworkMapper: GenreNetworkMapper
):EntityMapper<GenreIDNetworkEntity,FilterGC> {
    override fun mapFromEntity(entity: GenreIDNetworkEntity): FilterGC {
        return FilterGC(
            genres = entity.genres?.let { genreNetworkMapper.mapFromEntityList(it) }
        )
    }

    override fun mapToEntity(domainModel: FilterGC): GenreIDNetworkEntity {
       return GenreIDNetworkEntity(
           genres = domainModel.genres?.let { genreNetworkMapper.mapToEntityList(it) }

       )
    }
}