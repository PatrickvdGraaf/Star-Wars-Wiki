package nl.graaf.starwarswiki.api.character

import io.reactivex.Observable
import nl.graaf.starwarswiki.api.CharacterResult
import nl.graaf.starwarswiki.api.StarWarsApiService
import nl.graaf.starwarswiki.model.Film
import nl.graaf.starwarswiki.model.HomeWorld
import nl.graaf.starwarswiki.model.Vehicle

/**
 *
 * Created by Patrick van de Graaf on 6/7/2018.
 *
 */

class CharacterRepository(private val mService: StarWarsApiService) {
    fun getFilm(url: String): Observable<Film> {
        return mService.getFilm(url)
    }

    fun getVehicle(url: String): Observable<Vehicle> {
        return mService.getVehicle(url)
    }

    fun getCharacters(page: Int): Observable<CharacterResult> {
        return mService.getCharacters(page)
    }

    fun getHomeWorld(url:String): Observable<HomeWorld> {
        return mService.getHomeWorld(url)
    }
}