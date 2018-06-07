package nl.graaf.starwarswiki.ui.characters

import nl.graaf.starwarswiki.R
import nl.graaf.starwarswiki.model.Character

/**
 *
 * Created by Patrick van de Graaf on 6/7/2018.
 *
 */
enum class SortingType {
    BIRTH, NAME
}

class CharacterPresenter(private val mView: CharacterMVP.View) : CharacterMVP.Presenter {
    private val mInteractor by lazy { CharacterInteractor(this) }
    private val mCharacters = mutableListOf<Character>()

    private var mSortingType = SortingType.NAME

    override fun showCharacters() {
        mInteractor.getCharactersFromApi()
    }

    override fun onGetCharacters(characters: List<Character>) {
        mCharacters.addAll(characters)
        sortCharacters()
    }

    override fun getTotalCharactersCount() = mCharacters.size

    override fun onBindViewHolder(holder: CharacterAdapter.CharacterViewHolder, position: Int) {
        val character = mCharacters[position]
        holder.nameTextView.text = character.name
        holder.birthTextView.text = character.birthYear

        holder.favButton.setOnClickListener({
            //TODO
        })
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