package android.watch_movie.cache.mapper

import android.watch_movie.cache.entity.FilmCacheEntity
import android.watch_movie.cache.entity.RandomFilmCacheEntity
import android.watch_movie.model.Film
import android.watch_movie.util.EntityMapper
import javax.inject.Inject


class RandomFilmCacheMapper
@Inject
constructor(): EntityMapper<RandomFilmCacheEntity, Film> {
    override fun mapFromEntity(entity: RandomFilmCacheEntity):Film  {
        return Film(
            filmId = entity.filmId,
            nameRu = entity.nameRu,
            nameEn = entity.nameEn,
            year = entity.year,
            filmLength = entity.filmLength,
            countries = entity.countries,
            genres = entity.genres,
            rating = entity.rating,
            ratingVoteCount = entity.ratingVoteCount,
            posterUrl = entity.posterUrl,
            posterUrlPreview = entity.posterUrlPreview,
            ratingChange = entity.ratingChange,
            type = entity.type
        )
    }

    override fun mapToEntity(domainModel: Film): RandomFilmCacheEntity {
        return RandomFilmCacheEntity(
            filmId = domainModel.filmId,
            nameRu = domainModel.nameRu,
            nameEn = domainModel.nameEn,
            year = domainModel.year,
            filmLength = domainModel.filmLength,
            rating = domainModel.rating,
            ratingVoteCount = domainModel.ratingVoteCount,
            posterUrl = domainModel.posterUrl,
            countries = domainModel.countries,
            genres = domainModel.genres,
            posterUrlPreview = domainModel.posterUrlPreview,
            ratingChange = domainModel.ratingChange,
            type = domainModel.type
        )
    }
    fun mapFromEntityList(entities:List<RandomFilmCacheEntity>):List<Film>{
        return entities.map{ mapFromEntity(it)}
    }
}