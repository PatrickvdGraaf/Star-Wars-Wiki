package nl.graaf.starwarswiki.injection.module

import android.arch.persistence.room.Room
import android.content.Context
import dagger.Module
import nl.graaf.starwarswiki.database.CharacterDatabase

@Module
object RoomModule {
    private lateinit var database: CharacterDatabase
//    companion object {
//        private var INSTANCE: CharacterDatabase? = null
//
//        fun getInstance(context: Context): CharacterDatabase? {
//            if (INSTANCE == null) {
//                synchronized(CharacterDatabase::class) {
//                    INSTANCE = Room.databaseBuilder(context.applicationContext,
//                            CharacterDatabase::class.java, "characters.db")
//                            .build()
//                }
//            }
//            return INSTANCE
//        }
//
//        fun destroyInstance() {
//            INSTANCE = null
//        }
//    }
}