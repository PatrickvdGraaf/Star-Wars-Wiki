package nl.graaf.starwarswiki.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 *
 * Created by Patrick van de Graaf on 6/7/2018.
 *
 */

data class Character(
        val name: String,
        @SerializedName("birth_year")
        val birthYear: String,
        @SerializedName("films")
        val filmUrls: List<String>,
        @SerializedName("vehicles")
        val vehicleUrls: List<String>,
        @SerializedName("homeworld")
        val homeWorldUrl: String,
        var filmsList: List<Film> = listOf(),
        var vehiclesList: List<Vehicle> = listOf(),
        var home: HomeWorld? = null,
        var isFavourite: Boolean = false
) : Serializable {
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