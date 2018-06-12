package nl.graaf.starwarswiki.utils

import android.databinding.BindingAdapter
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.graphics.drawable.Drawable
import android.support.annotation.DrawableRes
import android.widget.ImageButton
import nl.graaf.starwarswiki.ui.characters.CharacterAdapter

/**
 * Sets an adapter to a [RecyclerView] (to be used in view with one [RecyclerView])
 * @param view the [RecyclerView] on which to set the adapter
 * @param adapter the adapter to set to the [RecyclerView]
 */
@BindingAdapter("adapter")
fun setAdapter(view: RecyclerView, adapter: CharacterAdapter) {
    view.adapter = adapter
}

/**
 * Sets a [LayoutManager] to a [RecyclerView] (to be used in view with one [RecyclerView])
 * @param view the [RecyclerView] on which to set the [LayoutManager]
 * @param layoutManager the [LayoutManager] to set to the [RecyclerView]
 */
@BindingAdapter("layoutManager")
fun setLayoutManager(view: RecyclerView, layoutManager: RecyclerView.LayoutManager) {
    view.layoutManager = layoutManager
}

/**
 * Adds a DividerItemDecoration to a [RecyclerView] (to be used in view with one [RecyclerView])
 * @param view the [RecyclerView] on which to set the [DividerItemDecoration]
 * @param dividerItemDecoration the [DividerItemDecoration] to set to the [RecyclerView]
 */
@BindingAdapter("dividerItemDecoration")
fun setDividerItemDecoration(view: RecyclerView, dividerItemDecoration: DividerItemDecoration) {
    view.addItemDecoration(dividerItemDecoration)
}

/**
 * Sets the icon for a button.
 * @param view the [ImageButton] on which to set the icon
 * @param icon the [Drawable] to set in the [ImageButton]
 */
@BindingAdapter("buttonIcon")
fun setButtonIcon(view: ImageButton, @DrawableRes icon: Int) {
    view.setImageResource(icon)
}