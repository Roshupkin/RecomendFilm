package android.watch_movie.network.mapper

import android.watch_movie.model.Country
import android.watch_movie.network.entity.CountryNetworkEntiy
import android.watch_movie.util.EntityMapper
import javax.inject.Inject

class CountryNetworkMapper
@Inject
constructor() : EntityMapper<CountryNetworkEntiy, Country> {
    override fun mapFromEntity(entity: CountryNetworkEntiy): Country {
        return Country(
            country = entity.country
        )
    }

    override fun mapToEntity(domainModel: Country): CountryNetworkEntiy {
        return CountryNetworkEntiy(
            country = domainModel.country
        )
    }

    fun mapFromEntityList(entites: List<CountryNetworkEntiy>): List<Country> {
        return entites.map { mapFromEntity(it) }
    }
    fun mapToEntityList(entites: List<Country>): List<CountryNetworkEntiy> {
        return entites.map { mapToEntity(it) }
    }
}