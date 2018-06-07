package nl.graaf.starwarswiki.ui.detail

import nl.graaf.starwarswiki.model.Film
import nl.graaf.starwarswiki.model.Vehicle

/**
 *
 * Created by Patrick van de Graaf on 6/7/2018.
 *
 */

class DetailPresenter(private val mView: DetailMVP.View) : DetailMVP.Presenter {
    private val mInteractor by lazy { DetailInteractor(this) }

    private var mFilms = listOf<Film>()
    private var mVehicles = listOf<Vehicle>()

    override fun showMovies(links: List<String>) {
        mInteractor.getFilmsFromApi(links)
    }

    override fun showVehicles(urls: List<String>) {
        mInteractor.getVehiclesFromApi(urls)
    }

    override fun onGetVehiclesResponse(vehicles: List<Vehicle>) {
        mVehicles = vehicles

        var v = ""
        mVehicles.forEach({
            v += "${it.name}\n"
        })

        mView.setVehicleText(v)
    }

    override fun onGetFilmsResponse(films: List<Film>) {
        mFilms = films.toMutableList()

        var movies = ""
        mFilms.forEach({
            movies += "${it.title}\n"
        })

        mView.setMovieText(movies)
    }
}