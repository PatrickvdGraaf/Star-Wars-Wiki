package nl.graaf.starwarswiki.ui.detail

import nl.graaf.starwarswiki.model.Film
import nl.graaf.starwarswiki.model.HomeWorld
import nl.graaf.starwarswiki.model.Vehicle

/**
 *
 * Created by Patrick van de Graaf on 6/7/2018.
 *
 */

class DetailMVP {
    interface View {
        fun setMovieText(m: String)
        fun setVehicleText(v: String)
        fun setHomeWorldText(h: String)
    }

    interface Presenter {
        fun loadMovies()
        fun onGetFilmsResponse(films: List<Film>)
        fun loadVehicles()
        fun onGetVehiclesResponse(vehicles: List<Vehicle>)
        fun loadHomeWorld()
        fun onGetHomeWorldResponse(homeWorld: HomeWorld)
    }

    interface Interactor {
        fun getFilmsFromApi(urls: List<String>)
        fun getVehiclesFromApi(links: List<String>)
        fun getHomeWorldFromApi(link: String)
    }
}