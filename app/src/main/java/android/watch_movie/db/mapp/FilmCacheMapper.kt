package android.watch_movie.db.mapp

import android.watch_movie.db.onetomany.FilmWithGenreandCountry
import android.watch_movie.model.Film
import android.watch_movie.util.EntityMapper
import javax.inject.Inject

class FilmCacheMapper
@Inject constructor() : EntityMapper<FilmWithGenreandCountry, Film> {
    lateinit var genreCacheMapper: GenreCacheMapper
    lateinit var countryCacheMapper: CountryCacheMapper

    override fun mapFromEntity(entity: FilmWithGenreandCountry): Film {
        return Film(
            filmId = entity.filmId,
            nameRu = entity.nameRu,
            nameEn = entity.nameEn,
            year = entity.year,
            filmLength = entity.filmLength,
            rating = entity.rating,
            ratingVoteCount = entity.ratingVoteCount,
            posterUrl = entity.posterUrl,
            posterUrlPreview = entity.posterUrlPreview,
            countries = entity.countrysEntity?.let { countryCacheMapper.mapFromEntityList(it) },
            genres = entity.genresEntity?.let { genreCacheMapper.mapFromEntityList(it) },
            ratingChange = entity.rating
        )
    }

    override fun mapToEntity(domainModel: Film): FilmWithGenreandCountry {
        return FilmWithGenreandCountry(
            filmId = domainModel.filmId,
            nameRu = domainModel.nameRu,
            nameEn = domainModel.nameEn,
            year = domainModel.year,
            filmLength = domainModel.filmLength,
            rating = domainModel.rating,
            ratingVoteCount = domainModel.ratingVoteCount,
            posterUrl = domainModel.posterUrl,
            posterUrlPreview = domainModel.posterUrlPreview,
            countrysEntity = domainModel.countries?.let { countryCacheMapper.mapToEntityList(it) },
            genresEntity = domainModel.genres?.let { genreCacheMapper.mapToEntityList(it) }, id = 0

        )
    }

    fun mapFromEntityList(entites: List<FilmWithGenreandCountry>): List<Film> {
        return entites.map { mapFromEntity(it) }
    }

    fun mapToEntityList(entites: List<Film>): List<FilmWithGenreandCountry> {
        return entites.map { mapToEntity(it) }
    }

}