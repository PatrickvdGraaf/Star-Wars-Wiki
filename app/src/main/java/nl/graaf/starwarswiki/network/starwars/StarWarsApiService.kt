package nl.graaf.starwarswiki.network.starwars

import io.reactivex.Observable
import nl.graaf.starwarswiki.model.Film
import nl.graaf.starwarswiki.model.HomeWorld
import nl.graaf.starwarswiki.model.Vehicle
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

/**
 * The interface which provides methods to get result of webservices
 *
 * Created by Patrick van de Graaf on 6/7/2018.
 */

interface StarWarsApiService {
    /**
     * Get the list of the characters from the API
     */
    @GET("people/")
    fun getCharacters(@Query("page") page: Int): Observable<CharacterResponse>

    @GET
    fun getFilm(@Url url: String): Observable<Film>

    @GET
    fun getVehicle(@Url url: String): Observable<Vehicle>

    @GET
    fun getHomeWorld(@Url url: String): Observable<HomeWorld>
}