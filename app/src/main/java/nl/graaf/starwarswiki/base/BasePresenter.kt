package nl.graaf.starwarswiki.base

import nl.graaf.starwarswiki.injection.component.DaggerPresenterInjector
import nl.graaf.starwarswiki.injection.component.PresenterInjector
import nl.graaf.starwarswiki.injection.module.ContextModule
import nl.graaf.starwarswiki.injection.module.NetworkModule
import nl.graaf.starwarswiki.ui.characters.CharacterPresenter

abstract class BasePresenter<out V : BaseView>(protected val view: V) {
    /**
     * The injector used to inject required dependencies
     */
    private val injector: PresenterInjector = DaggerPresenterInjector
            .builder()
            .baseView(view)
            .contextModule(ContextModule)
            .networkModule(NetworkModule)
            .build()

    init {
        inject()
    }

    /**
     * This method may be called when the presenter view is created
     */
    open fun onViewCreated() {}

    /**
     * This method may be called when the presenter view is destroyed
     */
    open fun onViewDestroyed() {}

    /**
     * Injects the required dependencies
     */
    private fun inject() {
        when (this) {
            is CharacterPresenter -> injector.inject(this)
        }
    }
}