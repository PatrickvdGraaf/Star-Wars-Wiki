package nl.graaf.starwarswiki.ui.detail

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import nl.graaf.starwarswiki.api.character.CharacterRepositoryProvider
import nl.graaf.starwarswiki.model.Film
import nl.graaf.starwarswiki.model.Vehicle
import timber.log.Timber

/**
 *
 * Created by Patrick van de Graaf on 6/7/2018.
 *
 */
class DetailInteractor(private val mPresenter: DetailMVP.Presenter) : DetailMVP.Interactor {
    override fun getVehiclesFromApi(urls: List<String>) {
        val repository = CharacterRepositoryProvider.provideCharacterRepository()
        val requests = ArrayList<Observable<Vehicle>>()

        urls.forEach({ url ->
            requests.add(repository.getVehicle(url))
        })

        Observable
                .zip(requests) { results ->
                    results
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    val list = mutableListOf<Vehicle>()
                    response.forEach {
                        if (it is Vehicle) {
                            list.add(it)
                        }
                    }
                    mPresenter.onGetVehiclesResponse(list)
                }, { error ->
                    Timber.d("Get Vehicle API call. \nError: ${error.localizedMessage}")
                })
    }

    override fun getFilmsFromApi(urls: List<String>) {
        val repository = CharacterRepositoryProvider.provideCharacterRepository()
        val requests = ArrayList<Observable<Film>>()

        urls.forEach({ url ->
            requests.add(repository.getFilm(url))
        })

        Observable
                .zip(requests) { results ->
                    results
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    val list = mutableListOf<Film>()
                    response.forEach {
                        if (it is Film) {
                            list.add(it)
                        }
                    }

                    list.sortWith(Comparator { o1, o2 ->
                        o1.episode.compareTo(o2.episode)
                    })

                    mPresenter.onGetFilmsResponse(list)
                }, { error ->
                    Timber.d("Get Film API call. \nError: ${error.localizedMessage}")
                })
    }
}