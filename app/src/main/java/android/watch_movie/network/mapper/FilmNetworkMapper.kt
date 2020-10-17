package android.watch_movie.network.mapper

import android.watch_movie.model.Film
import android.watch_movie.network.entity.FilmNetworkEntity
import android.watch_movie.util.EntityMapper
import javax.inject.Inject

class FilmNetworkMapper
@Inject
constructor() : EntityMapper<FilmNetworkEntity, Film> {
    lateinit var genreNetworkMapper: GenreNetworkMapper
    lateinit var countryNetworkMapper: CountryNetworkMapper
    override fun mapFromEntity(entity: FilmNetworkEntity): Film {
        return Film(
            filmId = entity.filmId,
            nameRu = entity.nameRu,
            nameEn = entity.nameEn,
            year = entity.year,
            filmLength = entity.filmLength,
            countries = entity.countries?.let { countryNetworkMapper.mapFromEntityList(it) },
            genres = entity.genres?.let { genreNetworkMapper.mapFromEntityList(it) },
            rating = entity.rating,
            ratingVoteCount = entity.ratingVoteCount,
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
            countries = domainModel.countries?.let { countryNetworkMapper.mapToEntityList(it) },
            genres = domainModel.genres?.let { genreNetworkMapper.mapToEntityList(it) },
            rating = domainModel.rating,
            ratingVoteCount = domainModel.ratingVoteCount,
            posterUrl = domainModel.posterUrl,
            posterUrlPreview = domainModel.posterUrlPreview,
            ratingChange = domainModel.ratingChange
        )
    }

    fun mapFromEntityList(entities: List<FilmNetworkEntity>): List<Film> {
        return entities.map { mapFromEntity(it) }
    }
    fun mapToEntityList(entities: List<Film>): List<FilmNetworkEntity> {
        return entities.map { mapToEntity(it) }
    }
}