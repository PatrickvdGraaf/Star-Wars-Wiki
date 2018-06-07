package nl.graaf.starwarswiki.database

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import android.arch.persistence.room.Update
import nl.graaf.starwarswiki.model.Character

/**
 *
 * Created by Patrick van de Graaf on 6/7/2018.
 *
 */

@Dao
interface CharacterDataDao {
    @Query("SELECT * from characterData")
    fun getAll(): List<Character>

    @Insert(onConflict = REPLACE)
    fun insertAll(characters: List<Character>)

    @Update(onConflict = REPLACE)
    fun updateCharacter(character: Character)
}