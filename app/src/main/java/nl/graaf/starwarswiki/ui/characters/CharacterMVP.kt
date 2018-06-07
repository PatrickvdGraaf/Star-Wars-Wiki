package nl.graaf.starwarswiki.ui.characters

import android.content.Context
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
        fun openDetailActivity(character: Character)
    }

    interface Presenter {
        fun showCharacters(context: Context)
        fun toggleSort()
        fun onGetCharacters(characters: List<Character> = listOf())
        fun getTotalCharactersCount(): Int
        fun onBindViewHolder(holder: CharacterAdapter.CharacterViewHolder, position: Int)
        fun onItemSelected(layoutPosition: Int)
        fun onError()
        fun onDestroy()
    }

    interface Interactor {
        fun getCharactersFromApi(page: Int = 1)
    }
}