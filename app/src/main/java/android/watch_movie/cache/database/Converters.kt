package android.watch_movie.cache.database

import android.watch_movie.network.entity.CountryNetworkEntity
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class Converters {
    @TypeConverter
    fun fromListOfStrings(listOfStrings:List<String>):String{
        return listOfStrings.joinToString (",")
    }

    @TypeConverter
    fun toListOfString(flatStringsList:String):List<String>{
        return flatStringsList.split(",")
    }

    /*Convert Integer*/
    @TypeConverter
    fun fromListIntOfString(listOfStrings:List<Int>): String {
        return listOfStrings.joinToString (",")
    }

    @TypeConverter
    fun toListIntOfString(flatStringsList:String): List<Int> {
        var listInt = listOf<Int>()
        val listString = flatStringsList.split(",")
        for (string in listString){
          listInt = listOf(string.toInt())
        }
        return listInt
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

