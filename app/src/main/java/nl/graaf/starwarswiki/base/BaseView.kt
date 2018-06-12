package nl.graaf.starwarswiki.base

import android.content.Context

/**
 * Base view any view must implement.
 */
interface BaseView {
    /**
     * @return the context in which the application is running
     */
    fun getContext(): Context
}