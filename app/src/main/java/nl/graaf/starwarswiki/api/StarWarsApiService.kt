package nl.graaf.starwarswiki.api

import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

/**
 *
 * Created by Patrick van de Graaf on 6/7/2018.
 *
 */

interface StarWarsApiService {
    @GET("people/")
    fun getCharacters(@Query("page") page: Int): Observable<CharacterResult>

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