package nl.graaf.starwarswiki.ui.base

import io.reactivex.disposables.Disposable

interface iApiPresenter {
    fun onDestroy()
    fun addToSubscription(disposable: Disposable)
}