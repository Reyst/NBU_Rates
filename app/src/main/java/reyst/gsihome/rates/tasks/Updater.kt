package reyst.gsihome.rates.tasks

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import reyst.gsihome.rates.App

class Updater(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {
    override fun doWork(): Result {
        App.instance.updateRepository.update()
        return Result.success()
    }
}