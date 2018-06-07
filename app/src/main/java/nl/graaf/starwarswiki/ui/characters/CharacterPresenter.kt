package nl.graaf.starwarswiki.ui.characters

import android.content.Context
import nl.graaf.starwarswiki.R
import nl.graaf.starwarswiki.database.CharacterDatabase
import nl.graaf.starwarswiki.model.Character
import nl.graaf.starwarswiki.ui.base.DbPresenter

/**
 *
 * Created by Patrick van de Graaf on 6/7/2018.
 *
 */
enum class SortingType {
    BIRTH, NAME
}

class CharacterPresenter(context: Context, private val mView: CharacterMVP.View )
    : CharacterMVP.Presenter, DbPresenter(context) {
    private val mInteractor by lazy { CharacterInteractor(this) }
    private val mCharacters = mutableListOf<Character>()

    private var mSortingType = SortingType.NAME

    override fun showCharacters(context: Context) {
        mDb = CharacterDatabase.getInstance(context)
        fetchCharactersFromDb({ data ->
            if (data == null || data.isEmpty()) {
                mInteractor.getCharactersFromApi()
            } else {
                mUiHandler.post({
                    data.let {
                        mCharacters.addAll(it)
                        sortCharacters()
                    }
                })
            }
        })
    }

    override fun onGetCharacters(characters: List<Character>) {
        mCharacters.addAll(characters)
        insertCharacterDataInDb(characters)
        sortCharacters()
    }

    override fun getTotalCharactersCount() = mCharacters.size

    override fun onBindViewHolder(holder: CharacterAdapter.CharacterViewHolder, position: Int) {
        val character = mCharacters[position]
        holder.nameTextView.text = character.name
        holder.birthTextView.text = character.birthYear

        holder.setFavouriteIcon(character.isFavourite)

        holder.favButton.setOnClickListener({
            character.toggleFavourite()
            holder.setFavouriteIcon(character.isFavourite)
            updateCharacterInDb(character)
        })
    }

    override fun onDestroy() {
        CharacterDatabase.destroyInstance()
        mDbWorkerThread.quit()
    }

    override fun onItemSelected(layoutPosition: Int) {
        mView.openDetailActivity(mCharacters[layoutPosition])
    }

    override fun toggleSort() {
        when (mSortingType) {
            SortingType.NAME -> {
                mSortingType = SortingType.BIRTH
                mView.setSortingButtonIcon(R.drawable.ic_sort_name_24dp)
            }
            SortingType.BIRTH -> {
                mSortingType = SortingType.NAME
                mView.setSortingButtonIcon(R.drawable.ic_birth_24dp)
            }
        }

        sortCharacters()
    }

    private fun sortCharacters() {
        when (mSortingType) {
            SortingType.NAME -> {
                sortByName()
            }
            SortingType.BIRTH -> {
                sortByBirth()
            }
        }
        mView.setCharacterList()
    }

    private fun sortByName() {
        mCharacters.sortWith(Comparator { o1, o2 ->
            o1.name.compareTo(o2.name)
        })
    }

    private fun sortByBirth() {
        mCharacters.sortWith(Comparator { o1, o2 ->
            if (o1.birthYear == "unknown" && o2.birthYear == "unknown") {
                0
            } else if (o1.birthYear == "unknown" && o2.birthYear != "unknown") {
                1
            } else if (o1.birthYear != "unknown" && o2.birthYear == "unknown") {
                -1
            } else {
                val period1 = o1.getPeriod()
                val period2 = o2.getPeriod()
                if (period1 == "BBY" && period2 == "ABY") {
                    1
                } else if (period1 == "ABY" && period2 == "BBY") {
                    -1
                } else if (period1 == "BBY") {
                    o1.getYear().compareTo(o2.getYear())
                } else {
                    o2.getYear().compareTo(o1.getYear())
                }
            }
        })
    }

    override fun onError() {
        mView.showError()
    }
}