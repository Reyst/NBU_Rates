@file:Suppress("DEPRECATION")

package reyst.gsihome.rates

import android.app.Application
import android.arch.persistence.room.Room
import android.content.Context
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import reyst.gsihome.rates.data.DefaultExchangeRateRepository
import reyst.gsihome.rates.data.DefaultUpdateRepository
import reyst.gsihome.rates.data.local.LocalDataSource
import reyst.gsihome.rates.data.local.room.AppDatabase
import reyst.gsihome.rates.data.local.room.RoomDataSource
import reyst.gsihome.rates.data.remote.RemoteDataSource
import reyst.gsihome.rates.data.remote.retrofit.RestService
import reyst.gsihome.rates.data.remote.retrofit.RetrofitDataSource
import reyst.gsihome.rates.domain.ExchangeRateRepository
import reyst.gsihome.rates.domain.UpdateRepository
import reyst.gsihome.rates.tasks.Updater
import java.util.concurrent.TimeUnit


class App : Application() {

    private val localDataSource: LocalDataSource by lazy {
        Room.databaseBuilder(applicationContext, AppDatabase::class.java, "database")
            .build()
            .let { RoomDataSource(it) }
    }

    private val remoteDataSource: RemoteDataSource by lazy {
        Retrofit.Builder()
            .addConverterFactory(SimpleXmlConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(RestService::class.java)
            .let { RetrofitDataSource(it, getString(R.string.date_format)) }
    }

    val repository: ExchangeRateRepository by lazy {
        DefaultExchangeRateRepository(localDataSource, remoteDataSource)
    }

    val updateRepository: UpdateRepository by lazy {
        DefaultUpdateRepository(localDataSource, remoteDataSource)
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        startUpdater()
    }

    private fun startUpdater() {
        val preferences = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val taskId = preferences.getString(KEY_TASK_ID, null)

        if (taskId == null) {

            val constraints = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build()

            val updateRequest = PeriodicWorkRequest
                .Builder(Updater::class.java, 1, TimeUnit.HOURS)
                .setConstraints(constraints)
                .build()

            WorkManager.getInstance().enqueue(updateRequest)

            preferences.edit()
                .putString(KEY_TASK_ID, updateRequest.id.toString())
                .apply()
        }
    }

    companion object {

        private const val PREF_NAME = "tasks"
        private const val KEY_TASK_ID = "task_id"
        private const val BASE_URL = "https://bank.gov.ua/NBUStatService/v1/"

        lateinit var instance: App
            private set
    }
}