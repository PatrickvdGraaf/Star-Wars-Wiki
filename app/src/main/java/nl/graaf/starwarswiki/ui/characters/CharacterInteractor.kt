package nl.graaf.starwarswiki.ui.characters

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import nl.graaf.starwarswiki.api.character.CharacterRepositoryProvider
import timber.log.Timber

/**
 *
 * Created by Patrick van de Graaf on 6/7/2018.
 *
 */

class CharacterInteractor(private val mPresenter: CharacterMVP.Presenter) : CharacterMVP.Interactor {
    override fun getCharactersFromApi(page: Int) {
        val repository = CharacterRepositoryProvider.provideCharacterRepository()

        repository.getCharacters(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    if (response.next == null) {
                        mPresenter.onGetCharacters(response.results)
                    } else {
                        mPresenter.onGetCharacters(response.results)
                        getCharactersFromApi(page + 1)
                    }
                }, { error ->
                    Timber.d("Get Characters API call. \nError: ${error.localizedMessage}")

                    mPresenter.onError()
                })
    }

}