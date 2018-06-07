package nl.graaf.starwarswiki.database

import android.os.Handler
import android.os.HandlerThread

/**
 *
 * Created by Patrick van de Graaf on 6/7/2018.
 *
 */

class DbWorkerThread(threadName: String) : HandlerThread(threadName) {

    private lateinit var mWorkerHandler: Handler

    override fun onLooperPrepared() {
        super.onLooperPrepared()
        mWorkerHandler = Handler(looper)
    }

    fun postTask(task: Runnable) {
        if (looper != null) {
            mWorkerHandler = Handler(looper)
        }
        mWorkerHandler.post(task)
    }
}