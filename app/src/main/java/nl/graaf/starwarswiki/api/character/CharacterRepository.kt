package nl.graaf.starwarswiki.api.character

import io.reactivex.Observable
import nl.graaf.starwarswiki.api.CharacterResult
import nl.graaf.starwarswiki.api.StarWarsApiService

/**
 *
 * Created by Patrick van de Graaf on 6/7/2018.
 *
 */

class CharacterRepository(private val mService: StarWarsApiService) {
    fun getCharacters(page: Int): Observable<CharacterResult> {
        return mService.getCharacters(page)
    }
}