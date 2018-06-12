package nl.graaf.starwarswiki.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import android.content.Context
import nl.graaf.starwarswiki.model.Character
import javax.inject.Singleton

/**
 *
 * Created by Patrick van de Graaf on 6/7/2018.
 *
 */

@Database(entities = [(Character::class)], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class CharacterDatabase : RoomDatabase() {
    abstract fun characterDao(): CharacterDataDao
}