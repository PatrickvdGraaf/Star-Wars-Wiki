package nl.graaf.starwarswiki.model

import com.google.gson.annotations.SerializedName

/**
 *
 * Created by Patrick van de Graaf on 6/7/2018.
 *
 */

data class Character(
        val name: String,
        @SerializedName("birth_year")
        val birthYear: String,
        val films: List<String>,
        val vehicles: List<String>,
        @SerializedName("homeworld")
        val homeWorldUrl: String
) {
    fun getPeriod(): String {
        return birthYear.substring(birthYear.length - 3)
    }

    fun getYear(): Float {
        return (birthYear.replace(getPeriod(), "")).toFloat()
    }
}