package nl.graaf.starwarswiki.config

import android.arch.persistence.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import nl.graaf.starwarswiki.model.Film
import nl.graaf.starwarswiki.model.HomeWorld
import nl.graaf.starwarswiki.model.Vehicle
import java.util.*

/**
 *
 * Created by Patrick van de Graaf on 6/7/2018.
 *
 */

class Converters {
    private val gson = Gson()

    @TypeConverter
    fun stringToFilmsList(data: String?): List<Film> {

        if (data == null) {
            return Collections.emptyList()
        }

        val listType = object : TypeToken<List<Film>>() {
        }.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun filmsListToString(list: List<Film>): String {
        return Gson().toJson(list)
    }

    @TypeConverter
    fun stringToStringList(data: String?): List<String> {
        val gson = Gson()

        if (data == null) {
            return Collections.emptyList()
        }

        val listType = object : TypeToken<List<String>>() {
        }.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun stringListToString(list: List<String>): String {
        return Gson().toJson(list)
    }

    @TypeConverter
    fun stringToVehicleList(data: String?): List<Vehicle> {
        val gson = Gson()

        if (data == null) {
            return Collections.emptyList()
        }

        val listType = object : TypeToken<List<Vehicle>>() {
        }.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun vehicleListToString(list: List<Vehicle>): String {
        return Gson().toJson(list)
    }

    @TypeConverter
    fun stringToHomeWorld(data: String?): HomeWorld? {
        val gson = Gson()

        if (data == null) {
            return null
        }

        val listType = object : TypeToken<HomeWorld>() {
        }.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun  homeWorldToString(world: HomeWorld?): String {
        return Gson().toJson(world)
    }
}