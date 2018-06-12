package nl.graaf.starwarswiki.injection.component

import dagger.BindsInstance
import dagger.Component
import nl.graaf.starwarswiki.base.BaseView
import nl.graaf.starwarswiki.injection.module.ContextModule
import nl.graaf.starwarswiki.injection.module.NetworkModule
import nl.graaf.starwarswiki.ui.characters.CharacterPresenter
import javax.inject.Singleton

/**
 * Component providing inject() methods for presenters.
 */
@Singleton
@Component(modules = [(ContextModule::class), (NetworkModule::class)])
interface PresenterInjector {
    /**
     * Injects required dependencies into the specified PostPresenter.
     * @param postPresenter PostPresenter in which to inject the dependencies
     */
    fun inject(characterPresenter: CharacterPresenter)

    @Component.Builder
    interface Builder {
        fun build(): PresenterInjector

        fun networkModule(networkModule: NetworkModule): Builder
        fun contextModule(contextModule: ContextModule): Builder

        @BindsInstance
        fun baseView(baseView: BaseView): Builder
    }
}