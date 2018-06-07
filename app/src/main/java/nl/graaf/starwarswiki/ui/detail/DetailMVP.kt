package nl.graaf.starwarswiki.ui.detail

import nl.graaf.starwarswiki.model.Film
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
    }

    interface Presenter {
        fun showMovies(links: List<String>)
        fun onGetFilmsResponse(films: List<Film>)
        fun showVehicles(links: List<String>)
        fun onGetVehiclesResponse(vehicles: List<Vehicle>)
    }

    interface Interactor {
        fun getFilmsFromApi(urls: List<String>)
        fun getVehiclesFromApi(links: List<String>)
    }
}