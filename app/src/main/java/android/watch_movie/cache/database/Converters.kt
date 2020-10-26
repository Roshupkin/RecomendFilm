package android.watch_movie.cache.database

import android.watch_movie.network.entity.CountryNetworkEntity
import android.watch_movie.network.entity.GenreNetworkEntity
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class Converters {

    @TypeConverter
    fun fromGenres(genres: List<GenreNetworkEntity>?): String = Gson().toJson(genres)

    @TypeConverter
    fun toGenres(genresString: String): List<GenreNetworkEntity>? {
        val listType: Type = object : TypeToken<List<GenreNetworkEntity>>() {}.type
        return Gson().fromJson(genresString, listType)
    }


    @TypeConverter
    fun fromCountris(countries: List<CountryNetworkEntity>): String = Gson().toJson(countries)

    @TypeConverter
    fun toCountris(countriesString: String): List<CountryNetworkEntity> {
        val listType: Type = object : TypeToken<List<CountryNetworkEntity>>() {}.type
        return Gson().fromJson(countriesString, listType)
    }
    @TypeConverter
    fun fromRatingChange(ratingChange: Any?): String {
        return ratingChange.toString()
    }

    @TypeConverter
    fun toRatingChange(ratingChange: String): Any? {
        return ratingChange.any()
    }
}

