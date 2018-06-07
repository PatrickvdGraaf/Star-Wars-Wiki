package nl.graaf.starwarswiki.ui.detail

import nl.graaf.starwarswiki.model.Character
import nl.graaf.starwarswiki.model.Film
import nl.graaf.starwarswiki.model.HomeWorld
import nl.graaf.starwarswiki.model.Vehicle

/**
 *
 * Created by Patrick van de Graaf on 6/7/2018.
 *
 */

class DetailPresenter(private val mView: DetailMVP.View, private val mCharacter: Character) : DetailMVP.Presenter {
    private val mInteractor by lazy { DetailInteractor(this) }

    override fun loadMovies() {
        mInteractor.getFilmsFromApi(mCharacter.filmUrls)
    }

    override fun loadVehicles() {
        mInteractor.getVehiclesFromApi(mCharacter.vehicleUrls)
    }

    override fun loadHomeWorld() {
        mInteractor.getHomeWorldFromApi(mCharacter.homeWorldUrl)
    }

    override fun onGetVehiclesResponse(vehicles: List<Vehicle>) {
        mCharacter.vehiclesList = vehicles

        var v = ""
        vehicles.forEach({
            v += "${it.name}\n"
        })
        if (!v.isBlank()) {
            mView.setVehicleText(v)
        }
    }

    override fun onGetFilmsResponse(films: List<Film>) {
        mCharacter.filmsList = films

        var movies = ""
        films.forEach({
            movies += "${it.title}\n"
        })

        if (!movies.isBlank()) {
            mView.setMovieText(movies)
        }
    }

    override fun onGetHomeWorldResponse(homeWorld: HomeWorld) {
        mCharacter.home = homeWorld
        mView.setHomeWorldText(homeWorld.name)
    }
}