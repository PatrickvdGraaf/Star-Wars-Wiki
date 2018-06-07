package nl.graaf.starwarswiki.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 *
 * Created by Patrick van de Graaf on 6/7/2018.
 *
 */

@Entity(tableName = "characterData")
data class Character(
        @PrimaryKey(autoGenerate = true) var id: Long? = null,
        @ColumnInfo(name = "name") val name: String = "",
        @SerializedName("birth_year")
        @ColumnInfo(name = "birth_year") val birthYear: String = "unknown",
        @SerializedName("films")
        @ColumnInfo(name = "film_urls") val filmUrls: List<String> = listOf(),
        @SerializedName("vehicles")
        @ColumnInfo(name = "vehicle_urls") val vehicleUrls: List<String> = listOf(),
        @SerializedName("homeworld")
        @ColumnInfo(name = "homeworld_url")val homeWorldUrl: String = "",
        @ColumnInfo(name = "films") var filmsList: List<Film> = listOf(),
        @ColumnInfo(name = "vehicles") var vehiclesList: List<Vehicle> = listOf(),
        @ColumnInfo(name = "homeworld") var home: HomeWorld? = null,
        @ColumnInfo(name = "favourite")var isFavourite: Boolean = false
) : Serializable {

    @Ignore
    constructor() : this(null, "", "", listOfNotNull(), listOfNotNull(), "", listOfNotNull(),
            listOfNotNull(), null, false)

    fun toggleFavourite() {
        isFavourite = !isFavourite
    }

    fun getPeriod(): String {
        return birthYear.substring(birthYear.length - 3)
    }

    fun getYear(): Float {
        return (birthYear.replace(getPeriod(), "")).toFloat()
    }
}