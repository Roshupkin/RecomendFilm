package android.watch_movie.db.cacheentity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "countrys")
data class CountryCacheEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var filmId:Int = 0,
    @ColumnInfo(name = "country")
    var country: String

)