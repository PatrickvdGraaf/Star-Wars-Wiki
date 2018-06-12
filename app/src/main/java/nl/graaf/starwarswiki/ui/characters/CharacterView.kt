package nl.graaf.starwarswiki.ui.characters

import android.support.annotation.DrawableRes
import android.support.annotation.StringRes
import nl.graaf.starwarswiki.base.BaseView
import nl.graaf.starwarswiki.model.Character

/**
 * Interface providing required method for a view displaying posts
 */
interface CharacterView : BaseView {
    /**
     * Updates the previous characters by the specified ones
     * @param characters the list of characters that will replace existing ones
     */
    fun updateCharacters(characters: List<Character>)

    fun setSortingButtonIcon(@DrawableRes icon: Int)

    /**
     * Displays an error in the view
     * @param error the error to display in the view
     */
    fun showError(error: String)

    /**
     * Displays an error in the view
     * @param errorResId the resource id of the error to display in the view
     */
    fun showError(@StringRes errorResId: Int) {
        this.showError(getContext().getString(errorResId))
    }

    /**
     * Displays the loading indicator of the view
     */
    fun showLoading()

    /**
     * Hides the loading indicator of the view
     */
    fun hideLoading()
}