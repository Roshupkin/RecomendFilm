package android.watch_movie.network.mapper

import android.watch_movie.model.Film
import android.watch_movie.network.entity.FilmNetworkEntity
import android.watch_movie.util.EntityMapper
import javax.inject.Inject

class FilmNetworkMapper
@Inject
constructor(
    private val genreNetworkMapper: GenreNetworkMapper
) : EntityMapper<FilmNetworkEntity, Film> {
    override fun mapFromEntity(entity: FilmNetworkEntity): Film {
        return Film(
            filmId = entity.filmId,
            nameRu = entity.nameRu,
            nameEn = entity.nameEn,
            webUrl = entity.webUrl,
            posterUrl = entity.posterUrl,
            posterUrlPreview = entity.posterUrlPreview,
            year = entity.year,
            filmLength = entity.filmLength,
            slogan = entity.slogan,
            description = entity.description,
            type = entity.type,
            ratingMpa = entity.ratingMpa,
            ratingAgeLimits = entity.ratingAgeLimits,
            premiereRu = entity.premiereRu,
            distributors = entity.distributors,
            premiereWorld = entity.premiereWorld,
            premiereDigital = entity.premiereDigital,
            premiereWorldCountry = entity.premiereWorldCountry,
            premiereDvd = entity.premiereDvd,
            premiereBluRay = entity.premiereBluRay,
            distributorRelease = entity.distributorRelease,
            countries = entity.countries,
            facts = entity.facts,
            seasons = entity.seasons,
            genres = entity.genres?.let { genreNetworkMapper.mapFromEntityList(it) },
            rating = entity.rating,
            ratingVoteCount = entity.ratingVoteCount,
            ratingChange = entity.ratingChange
        )

    }

    override fun mapToEntity(domainModel: Film): FilmNetworkEntity {
        return FilmNetworkEntity(
            filmId = domainModel.filmId,
            nameRu = domainModel.nameRu,
            nameEn = domainModel.nameEn,
            webUrl = domainModel.webUrl,
            posterUrl = domainModel.posterUrl,
            posterUrlPreview = domainModel.posterUrlPreview,
            year = domainModel.year,
            filmLength = domainModel.filmLength,
            slogan = domainModel.slogan,
            description = domainModel.description,
            type = domainModel.type,
            ratingMpa = domainModel.ratingMpa,
            ratingAgeLimits = domainModel.ratingAgeLimits,
            premiereRu = domainModel.premiereRu,
            distributors = domainModel.distributors,
            premiereWorld = domainModel.premiereWorld,
            premiereDigital = domainModel.premiereDigital,
            premiereWorldCountry = domainModel.premiereWorldCountry,
            premiereDvd = domainModel.premiereDvd,
            premiereBluRay = domainModel.premiereBluRay,
            distributorRelease = domainModel.distributorRelease,
            countries = domainModel.countries,
            facts = domainModel.facts,
            seasons = domainModel.seasons,
            genres = domainModel.genres?.let { genreNetworkMapper.mapToEntityList(it) },
            rating = domainModel.rating,
            ratingVoteCount = domainModel.ratingVoteCount,
            ratingChange = domainModel.ratingChange
        )
    }

    fun mapFromEntityList(entities: List<FilmNetworkEntity>): List<Film> {
        return entities.map { mapFromEntity(it) }
    }

    fun mapToEntityList(domainModels: List<Film>): List<FilmNetworkEntity> {
        return domainModels.map { mapToEntity(it) }
    }
}