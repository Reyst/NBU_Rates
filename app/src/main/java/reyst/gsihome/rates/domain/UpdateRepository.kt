package reyst.gsihome.rates.domain

import android.support.annotation.WorkerThread

interface UpdateRepository {

    @WorkerThread
    fun update()
}