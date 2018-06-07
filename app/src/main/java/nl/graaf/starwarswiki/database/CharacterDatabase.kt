package nl.graaf.starwarswiki.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import android.content.Context
import nl.graaf.starwarswiki.config.Converters
import nl.graaf.starwarswiki.model.Character

/**
 *
 * Created by Patrick van de Graaf on 6/7/2018.
 *
 */

@Database(entities = [(Character::class)], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class CharacterDatabase : RoomDatabase() {
    abstract fun characterDao(): CharacterDataDao

    companion object {
        private var INSTANCE: CharacterDatabase? = null

        fun getInstance(context: Context): CharacterDatabase? {
            if (INSTANCE == null) {
                synchronized(CharacterDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                            CharacterDatabase::class.java, "characters.db")
                            .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}