package nl.graaf.starwarswiki.ui.character

import android.support.annotation.DrawableRes
import nl.graaf.starwarswiki.model.Character

/**
 *
 * Created by Patrick van de Graaf on 6/7/2018.
 *
 */

class CharacterMVP {
    interface View {
        fun setSortingButtonIcon(@DrawableRes image: Int)
        fun setCharacterList()
        fun showError()
    }

    interface Presenter {
        fun showCharacters()
        fun toggleSort()
        fun onGetCharacters(characters: List<Character> = listOf())
        fun getTotalCharactersCount(): Int
        fun onBindViewHolder(holder: CharacterAdapter.CharacterViewHolder, position: Int)
        fun onError()
    }

    interface Interactor {
        fun getCharactersFromApi(page: Int = 1)
    }
}