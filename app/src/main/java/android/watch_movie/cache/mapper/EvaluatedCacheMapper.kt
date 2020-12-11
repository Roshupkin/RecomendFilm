package android.watch_movie.cache.mapper

import android.watch_movie.cache.entity.EvaluatedFilmCache
import android.watch_movie.model.Film
import android.watch_movie.util.EntityMapper
import javax.inject.Inject

class EvaluatedCacheMapper
@Inject
constructor(
) : EntityMapper<EvaluatedFilmCache, Film> {
    override fun mapFromEntity(entity: EvaluatedFilmCache): Film {
        return Film(
            filmID = entity.filmID,
            nameRu = entity.nameRu,
            nameEn = entity.nameEn,
            rating = entity.rating,
            posterUrl = entity.posterUrl,
            evaluated = entity.evaluated
        )
    }

    override fun mapToEntity(domainModel: Film): EvaluatedFilmCache {
        return EvaluatedFilmCache(
            filmID = domainModel.filmID,
            nameRu = domainModel.nameRu,
            nameEn = domainModel.nameEn,
            rating = domainModel.rating,
            posterUrl = domainModel.posterUrl,
            evaluated = domainModel.evaluated
        )
    }
}