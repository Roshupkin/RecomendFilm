package android.watch_movie.db.mapp

import android.watch_movie.db.cacheentity.CountryCacheEntity
import android.watch_movie.model.Country
import android.watch_movie.network.entity.CountryNetworkEntiy
import android.watch_movie.util.EntityMapper
import javax.inject.Inject

class CountryCacheMapper
@Inject constructor():EntityMapper<CountryCacheEntity,Country>{
    override fun mapFromEntity(entity: CountryCacheEntity): Country {
        return Country(
            country = entity.country
        )
    }

    override fun mapToEntity(domainModel: Country): CountryCacheEntity {
       return  CountryCacheEntity(
           country = domainModel.country
       )
    }
    fun mapFromEntityList(entites: List<CountryCacheEntity>): List<Country> {
        return entites.map { mapFromEntity(it) }
    }
    fun mapToEntityList(entites: List<Country>): List<CountryCacheEntity> {
        return entites.map { mapToEntity(it) }
    }
}