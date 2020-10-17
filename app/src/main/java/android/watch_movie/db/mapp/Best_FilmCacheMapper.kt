package android.watch_movie.db.mapp


import android.watch_movie.db.onetomany.Best_FilmWithFilm
import android.watch_movie.db.onetomany.FilmWithGenreandCountry
import android.watch_movie.model.Best_Film
import android.watch_movie.util.EntityMapper
import javax.inject.Inject

class Best_FilmCacheMapper
    @Inject
    constructor() : EntityMapper<Best_FilmWithFilm,Best_Film>{
lateinit var filmCaheMapper: FilmCacheMapper

    override fun mapFromEntity(entity: Best_FilmWithFilm): Best_Film {
        return Best_Film(
           films = entity.filmEntity?.let { filmCaheMapper.mapFromEntityList(it) },
            pagesCount = entity.pagesCount
        )
    }

    override fun mapToEntity(domainModel: Best_Film): Best_FilmWithFilm {
        return Best_FilmWithFilm(
            filmEntity = domainModel.films?.let { filmCaheMapper.mapToEntityList(it) },
           pagesCount= domainModel.pagesCount

        )
    }


}
