package android.watch_movie.cache.mapper

import android.watch_movie.cache.entity.RandomFilmCache
import android.watch_movie.model.Film
import android.watch_movie.util.EntityMapper
import javax.inject.Inject


class RandomFilmCacheMapper
@Inject
constructor(
    private val genreCacheMapper: GenreCacheMapper
): EntityMapper<RandomFilmCache, Film> {
    override fun mapFromEntity(entity: RandomFilmCache):Film  {
        return Film(
            filmId = entity.filmId,
            countFilm = entity.countFilm,
            nameRu = entity.nameRu,
            nameEn = entity.nameEn,
            year = entity.year,
            filmLength = entity.filmLength,
            countries = entity.countries,
            genres = entity.genres,
            /*genres = entity.genres?.let { genreCacheMapper.mapFromEntityList(it) },*/
            rating = entity.rating,
            ratingVoteCount = entity.ratingVoteCount,
            posterUrl = entity.posterUrl,
            posterUrlPreview = entity.posterUrlPreview,
            ratingChange = entity.ratingChange,
            type = entity.type,
            description = entity.description
        )
    }

    override fun mapToEntity(domainModel: Film): RandomFilmCache {
        return RandomFilmCache(
            filmId = domainModel.filmId,
            countFilm = domainModel.countFilm,
            nameRu = domainModel.nameRu,
            nameEn = domainModel.nameEn,
            year = domainModel.year,
            filmLength = domainModel.filmLength,
            rating = domainModel.rating,
            ratingVoteCount = domainModel.ratingVoteCount,
            posterUrl = domainModel.posterUrl,
            countries = domainModel.countries,
            genres = domainModel.genres,
            /*genres = domainModel.genres?.let { genreCacheMapper.mapToEntityList(it) },*/
            posterUrlPreview = domainModel.posterUrlPreview,
            ratingChange = domainModel.ratingChange,
            type = domainModel.type,
            description = domainModel.description
        )
    }
    fun mapFromEntityList(entities:List<RandomFilmCache>):List<Film>{
        return entities.map{ mapFromEntity(it)}
    }
}