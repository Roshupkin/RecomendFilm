package android.watch_movie.network.mapper

import android.watch_movie.model.Film
import android.watch_movie.network.entity.FilmNetworkEntity
import android.watch_movie.util.EntityMapper
import javax.inject.Inject

class FilmNetworkMapper
@Inject
constructor():EntityMapper<FilmNetworkEntity,Film> {
    override fun mapFromEntity(entity: FilmNetworkEntity): Film {
        return Film(
             filmId = entity.filmId,
         nameRu = entity.nameRu,
         nameEn = entity.nameEn,
         year = entity.year,
        filmLength = entity.filmLength,
         countries = entity.countries,
         genres = entity.genres,
         rating = entity.rating,
         ratingVoteCount = entity. ratingVoteCount,
         posterUrl = entity.posterUrl,
         posterUrlPreview = entity.posterUrlPreview,
         ratingChange = entity.ratingChange
        )

    }

    override fun mapToEntity(domainModel: Film): FilmNetworkEntity {
        return FilmNetworkEntity(
            filmId = domainModel.filmId,
            nameRu = domainModel.nameRu,
            nameEn = domainModel.nameEn,
            year = domainModel.year,
            filmLength = domainModel.filmLength,
            countries = domainModel.countries,
            genres = domainModel.genres,
            rating = domainModel.rating,
            ratingVoteCount = domainModel.ratingVoteCount,
            posterUrl = domainModel.posterUrl,
            posterUrlPreview = domainModel.posterUrlPreview,
            ratingChange = domainModel.ratingChange
        )
    }
    fun mapFromEntityList(entities:List<FilmNetworkEntity>):List<Film>{
        return entities.map{ mapFromEntity(it)}
    }
    fun mapToEntityList(domainModels:List<Film>):List<FilmNetworkEntity>{
        return domainModels.map{ mapToEntity(it)}
    }
}