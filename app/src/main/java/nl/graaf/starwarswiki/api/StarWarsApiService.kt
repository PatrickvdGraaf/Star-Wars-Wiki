package nl.graaf.starwarswiki.api

import io.reactivex.Observable
import nl.graaf.starwarswiki.model.Film
import nl.graaf.starwarswiki.model.HomeWorld
import nl.graaf.starwarswiki.model.Vehicle
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

/**
 *
 * Created by Patrick van de Graaf on 6/7/2018.
 *
 */

interface StarWarsApiService {
    @GET("people/")
    fun getCharacters(@Query("page") page: Int): Observable<CharacterResult>

    @GET
    fun getFilm(@Url url: String): Observable<Film>

    @GET
    fun getVehicle(@Url url: String): Observable<Vehicle>

    @GET
    fun getHomeWorld(@Url url: String): Observable<HomeWorld>

    companion object {
        fun create(): StarWarsApiService {
            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("https://swapi.co/api/")
                    .build()
            return retrofit.create(StarWarsApiService::class.java)
        }
    }
}