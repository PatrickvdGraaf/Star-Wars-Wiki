package nl.graaf.starwarswiki.api.character

import nl.graaf.starwarswiki.api.StarWarsApiService

/**
 *
 * Created by Patrick van de Graaf on 6/7/2018.
 *
 */

object CharacterRepositoryProvider {
    fun provideCharacterRepository(): CharacterRepository {
        return CharacterRepository(StarWarsApiService.create())
    }
}