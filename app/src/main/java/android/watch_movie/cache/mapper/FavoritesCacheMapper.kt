package android.watch_movie.cache.mapper

import android.watch_movie.cache.entity.FavoritesFilmCache

import android.watch_movie.model.Film
import android.watch_movie.util.EntityMapper
import javax.inject.Inject

class FavoritesCacheMapper
@Inject
constructor(
) : EntityMapper<FavoritesFilmCache, Film> {
    override fun mapFromEntity(entity: FavoritesFilmCache): Film {
        return Film(
            filmID = entity.filmID,
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
            genres = entity.genres,
            facts = entity.facts,
            /* seasons = entity.seasons,*/
            rating = entity.rating,
            ratingVoteCount = entity.ratingVoteCount,
            ratingChange = entity.ratingChange,
        )
    }

    override fun mapToEntity(domainModel: Film): FavoritesFilmCache {
        return FavoritesFilmCache(
            filmID = domainModel.filmID,
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
            genres = domainModel.genres,
            facts = domainModel.facts,
            /*seasons = domainModel.seasons,*/
            rating = domainModel.rating,
            ratingVoteCount = domainModel.ratingVoteCount,
            ratingChange = domainModel.ratingChange,
        )
    }
}