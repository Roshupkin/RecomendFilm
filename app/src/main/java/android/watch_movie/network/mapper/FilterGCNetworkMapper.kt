package android.watch_movie.network.mapper

import android.watch_movie.model.FilterGC
import android.watch_movie.network.entity.FilterGCNetworkEntity
import android.watch_movie.util.EntityMapper
import javax.inject.Inject

class FilterGCNetworkMapper
@Inject
constructor(
    private val genreNetworkMapper: GenreNetworkMapper
):EntityMapper<FilterGCNetworkEntity,FilterGC> {
    override fun mapFromEntity(entity: FilterGCNetworkEntity): FilterGC {
        return FilterGC(
            genres = entity.genres?.let { genreNetworkMapper.mapFromEntityList(it) }
        )
    }

    override fun mapToEntity(domainModel: FilterGC): FilterGCNetworkEntity {
       return FilterGCNetworkEntity(
           genres = domainModel.genres?.let { genreNetworkMapper.mapToEntityList(it) }

       )
    }
}