package nl.graaf.starwarswiki.database

import nl.graaf.starwarswiki.model.Character

/**
 * Repository interface that exposes all available database actions.
 */
interface CharacterRepository {
    fun getAll(): List<Character>
    fun insertAll(characters: List<Character>)
    fun update(character: Character)
}