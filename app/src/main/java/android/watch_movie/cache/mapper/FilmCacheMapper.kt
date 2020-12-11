package android.watch_movie.cache.mapper

import android.watch_movie.cache.entity.FilmCache
import android.watch_movie.model.Film
import android.watch_movie.util.EntityMapper
import javax.inject.Inject

class FilmCacheMapper
@Inject constructor(
) : EntityMapper<FilmCache, Film> {
    override fun mapFromEntity(entity: FilmCache): Film {
        return Film(
            filmID = entity.filmID,
            loadCount = entity.countFilm,
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
            seasons = entity.seasons,
            rating = entity.rating,
            ratingVoteCount = entity.ratingVoteCount,
            ratingChange = entity.ratingChange,
        )
    }

    override fun mapToEntity(domainModel: Film): FilmCache {
        TODO("Not yet implemented")
    }
}