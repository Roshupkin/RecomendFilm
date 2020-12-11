package android.watch_movie.cache.mapper

import android.watch_movie.cache.entity.TopFilmCache
import android.watch_movie.model.Film
import android.watch_movie.util.EntityMapper
import javax.inject.Inject


class TopFilmCacheMapper
@Inject
constructor(
): EntityMapper<TopFilmCache, Film> {
    override fun mapFromEntity(entity: TopFilmCache):Film  {
        return Film(
            filmID = entity.filmId,
            nameRu = entity.nameRu,
            nameEn = entity.nameEn,
            year = entity.year,
            filmLength = entity.filmLength,
            countries = entity.countries,
            rating = entity.rating,
            ratingVoteCount = entity.ratingVoteCount,
            posterUrl = entity.posterUrl,
            posterUrlPreview = entity.posterUrlPreview,
            ratingChange = entity.ratingChange,
            type = entity.type
        )
    }

    override fun mapToEntity(domainModel: Film): TopFilmCache {
        return TopFilmCache(
            filmId = domainModel.filmID,
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
    fun mapFromEntityList(entities:List<TopFilmCache>):List<Film>{
        return entities.map{ mapFromEntity(it)}
    }
}