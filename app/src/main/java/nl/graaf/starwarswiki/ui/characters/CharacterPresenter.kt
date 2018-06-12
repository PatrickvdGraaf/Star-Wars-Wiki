package nl.graaf.starwarswiki.ui.characters

import android.content.Intent
import com.github.ajalt.timberkt.Timber
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import nl.graaf.starwarswiki.R
import nl.graaf.starwarswiki.base.BasePresenter
import nl.graaf.starwarswiki.model.Character
import nl.graaf.starwarswiki.network.starwars.StarWarsApiService
import nl.graaf.starwarswiki.ui.detail.DetailActivity
import javax.inject.Inject

/**
 *
 * Created by Patrick van de Graaf on 6/7/2018.
 *
 */

class CharacterPresenter(characterView: CharacterView)
    : BasePresenter<CharacterView>(characterView), CharacterAdapter.Listener {
    @Inject
    lateinit var characterApi: StarWarsApiService

    //    private val mInteractor by lazy { CharacterInteractor(this) }

    private var subscription: Disposable? = null

    override fun onViewCreated() {
        loadCharacters()
    }

    override fun onCharacterSelected(character: Character) {
        with(view.getContext()) {
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("character", character)
            startActivity(intent)
        }
    }

    private fun loadCharacters(page: Int = 1) {
        view.showLoading()
        subscription = characterApi.getCharacters(page)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnTerminate { view.hideLoading() }
                .subscribe(
                        { response ->
                            if (response.next != null) {
                                loadCharacters(page + 1)
                            }

                            view.updateCharacters(response.results)
                        },
                        { error ->
                            view.showError(R.string.unknown_error)
                            Timber.e(error)
                        }
                )
    }

    override fun updateCharacterInDb(character: Character) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


//    override fun showCharacters(context: Context) {
//        mDb = CharacterDatabase.getInstance(context)
//        fetchCharactersFromDb({ data ->
//            if (data == null || data.isEmpty()) {
//                mInteractor.getCharactersFromApi()
//            } else {
//                mCharacters = data.toMutableList()
//
//                mUiHandler.post({
//                    sortCharacters()
//                })
//            }
//        })
//    }

//    override fun setCharacters(characters: List<Character>) {
//        mCharacters = characters.toMutableList()
//        insertCharacterDataInDb(characters)
//        sortCharacters()
//
//        mCharacters.forEach({
//            addToSubscription(mInteractor.getCharacterDetail(it))
//        })
//    }

//    override fun onGetCharacterDetails(updatedCharacter: Character) {
//        updateCharacterInDb(updatedCharacter)
//        mCharacters.filter { character -> character.name == updatedCharacter.name }
//                .mapIndexed { index, _ ->
//                    mCharacters.set(index, updatedCharacter)
//                }
//    }

    override fun onViewDestroyed() {
        subscription?.dispose()
    }
}