package nl.graaf.starwarswiki.database

import nl.graaf.starwarswiki.model.Character
import javax.inject.Inject

class CharacterDataSource @Inject constructor(private val dao: CharacterDataDao)
    : CharacterRepository {
    override fun getAll(): List<Character> {
        return dao.getAll()
    }

    override fun insertAll(characters: List<Character>) {
        dao.insertAll(characters)
    }

    override fun update(character: Character) {
        dao.updateCharacter(character)
    }

}