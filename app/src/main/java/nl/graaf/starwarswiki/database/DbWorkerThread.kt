package nl.graaf.starwarswiki.database

import android.os.Handler
import android.os.HandlerThread

/**
 *
 * Created by Patrick van de Graaf on 6/7/2018.
 *
 */

class DbWorkerThread(threadName: String) : HandlerThread(threadName) {

    private var mWorkerHandler: Handler? = null

    override fun onLooperPrepared() {
        super.onLooperPrepared()
        mWorkerHandler = Handler(looper)
    }

    fun postTask(task: Runnable) {
        if (looper != null) {
            mWorkerHandler = Handler(looper)
        }
        mWorkerHandler?.post(task)
    }
}