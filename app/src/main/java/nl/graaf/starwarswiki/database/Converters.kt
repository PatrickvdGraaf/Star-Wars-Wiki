package nl.graaf.starwarswiki.database

import android.arch.persistence.room.TypeConverter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import nl.graaf.starwarswiki.model.Film
import nl.graaf.starwarswiki.model.HomeWorld
import nl.graaf.starwarswiki.model.Vehicle
import timber.log.Timber
import java.io.IOException
import java.util.*

class Converters {
    private val moshi = Moshi.Builder().build()

    @TypeConverter
    fun stringToFilmsList(data: String?): List<Film> {
        return try {
            val filmListData = Types.newParameterizedType(List::class.java,
                    Film::class.java)
            val adapter = moshi.adapter<List<Film>>(filmListData)
            adapter.fromJson(data)
        } catch (e: IOException) {
            Timber.e(e)
            Collections.emptyList()
        }
    }

    @TypeConverter
    fun filmsListToString(list: List<Film>): String {
        return try {
            val filmListData = Types.newParameterizedType(List::class.java,
                    Film::class.java)
            val adapter = moshi.adapter<List<Film>>(filmListData)
            adapter.toJson(list)
        } catch (e: IOException) {
            Timber.e(e)
            ""
        }
    }

    @TypeConverter
    fun stringToStringList(data: String?): List<String> {
        return try {
            val listData = Types.newParameterizedType(List::class.java,
                    String::class.java)
            val adapter = moshi.adapter<List<String>>(listData)
            adapter.fromJson(data)
        } catch (e: IOException) {
            Timber.e(e)
            Collections.emptyList()
        }
    }

    @TypeConverter
    fun stringListToString(list: List<String>): String {
        return try {
            val listData = Types.newParameterizedType(List::class.java,
                    String::class.java)
            val adapter = moshi.adapter<List<String>>(listData)
            adapter.toJson(list)
        } catch (e: IOException) {
            Timber.e(e)
            ""
        }
    }

    @TypeConverter
    fun stringToVehicleList(data: String?): List<Vehicle> {
        return try {
            val vehicleListData = Types.newParameterizedType(List::class.java,
                    Vehicle::class.java)
            val adapter = moshi.adapter<List<Vehicle>>(vehicleListData)
            adapter.fromJson(data)
        } catch (e: IOException) {
            Timber.e(e)
            Collections.emptyList()
        }
    }

    @TypeConverter
    fun vehicleListToString(list: List<Vehicle>): String {
        return try {
            val vehicleListData = Types.newParameterizedType(List::class.java,
                    Vehicle::class.java)
            val adapter = moshi.adapter<List<Vehicle>>(vehicleListData)
            adapter.toJson(list)
        } catch (e: IOException) {
            Timber.e(e)
            ""
        }
    }

    @TypeConverter
    fun stringToHomeWorld(data: String?): HomeWorld? {
        return try {
            val homeWorldData = Types.newParameterizedType(HomeWorld::class.java)
            val adapter = moshi.adapter<HomeWorld>(homeWorldData)
            adapter.fromJson(data)
        } catch (e: IOException) {
            Timber.e(e)
            null
        }
    }

    @TypeConverter
    fun homeWorldToString(world: HomeWorld?): String {
        return try {
            val homeWorldData = Types.newParameterizedType(HomeWorld::class.java)
            val adapter = moshi.adapter<HomeWorld>(homeWorldData)
            adapter.toJson(world)
        } catch (e: IOException) {
            Timber.e(e)
            ""
        }
    }
}