package nl.graaf.starwarswiki.ui.base

import android.content.Context
import android.net.ConnectivityManager
import android.os.Handler
import nl.graaf.starwarswiki.database.CharacterDatabase
import nl.graaf.starwarswiki.database.DbWorkerThread
import nl.graaf.starwarswiki.model.Character



/**
 *
 * Created by Patrick van de Graaf on 6/7/2018.
 *
 */
open class DbPresenter(context: Context) {
    protected var mDbWorkerThread: DbWorkerThread = DbWorkerThread("dbWorkerThread")
    protected var mDb: CharacterDatabase? = CharacterDatabase.getInstance(context)
    private val mConnectionManger = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    protected val mUiHandler = Handler()

    init {
        mDbWorkerThread.start()
    }

    protected fun fetchCharactersFromDb(onComplete: (data: List<Character>?) -> Unit) {
        if (!mDbWorkerThread.isAlive) {
            mDbWorkerThread.start()
        }

        val task = Runnable {
            val characterData = mDb?.characterDao()?.getAll()
            onComplete(characterData)
        }
        mDbWorkerThread.postTask(task)
    }

    protected fun insertCharacterDataInDb(list: List<Character>) {
        val task = Runnable { mDb?.characterDao()?.insertAll(list) }
        mDbWorkerThread.postTask(task)
    }

    protected fun updateCharacterInDb(character: Character) {
        if (!mDbWorkerThread.isAlive) {
            mDbWorkerThread.start()
        }
        val task = Runnable { mDb?.characterDao()?.updateCharacter(character) }
        mDbWorkerThread.postTask(task)
    }

    protected fun isNetworkConnected(): Boolean {
        return mConnectionManger.activeNetworkInfo != null
    }
}