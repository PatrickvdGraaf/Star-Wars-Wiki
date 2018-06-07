package nl.graaf.starwarswiki.ui.detail

import android.content.Context
import nl.graaf.starwarswiki.model.Character
import nl.graaf.starwarswiki.model.Film
import nl.graaf.starwarswiki.model.HomeWorld
import nl.graaf.starwarswiki.model.Vehicle
import nl.graaf.starwarswiki.ui.base.DbPresenter

/**
 *
 * Created by Patrick van de Graaf on 6/7/2018.
 *
 */

// TODO zip all data and show when everything is loaded

class DetailPresenter(context: Context,
                      private val mView: DetailMVP.View,
                      private val mCharacter: Character)
    : DetailMVP.Presenter, DbPresenter(context) {
    private val mInteractor by lazy { DetailInteractor(this) }

    override fun loadMovies() {
        if (mCharacter.filmsList.isEmpty()) {
            if (!isNetworkConnected()) {
                return
            }
            mInteractor.getFilmsFromApi(mCharacter.filmUrls)
        } else {
            setFilmsText(mCharacter.filmsList)
        }
    }

    override fun loadVehicles() {
        if (mCharacter.vehiclesList.isEmpty()) {
            if (!isNetworkConnected()) {
                return
            }
            mInteractor.getVehiclesFromApi(mCharacter.vehicleUrls)
        } else {
            setVehicleText(mCharacter.vehiclesList)
        }
    }

    override fun loadHomeWorld() {
        if (mCharacter.home == null) {
            if (!isNetworkConnected()) {
                return
            }
            mInteractor.getHomeWorldFromApi(mCharacter.homeWorldUrl)
        } else {
            mView.setHomeWorldText(mCharacter.home!!.name)
        }
    }

    override fun onGetVehiclesResponse(vehicles: List<Vehicle>) {
        mCharacter.vehiclesList = vehicles
        updateCharacterInDb(mCharacter)

        setVehicleText(vehicles)
    }

    private fun setVehicleText(vehicles: List<Vehicle>) {
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
        updateCharacterInDb(mCharacter)

        setFilmsText(films)
    }

    private fun setFilmsText(films: List<Film>) {
        var movies = ""
        films.forEach({
            movies += "${it.title}\n"
        })

        if (!movies.isBlank()) {
            mView.setMovieText(movies)
        }
    }

    override fun onDestroy() {
        mDbWorkerThread.quit()
    }

    override fun onGetHomeWorldResponse(homeWorld: HomeWorld) {
        mCharacter.home = homeWorld
        updateCharacterInDb(mCharacter)
        mView.setHomeWorldText(homeWorld.name)
    }
}