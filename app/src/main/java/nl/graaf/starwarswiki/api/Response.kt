package nl.graaf.starwarswiki.api

import nl.graaf.starwarswiki.model.Character

/**
 *
 * Created by Patrick van de Graaf on 6/7/2018.
 *
 */

data class CharacterResult(val count: Int,
                           val next: String?,
                           val previous: String?,
                           val results: List<Character>)