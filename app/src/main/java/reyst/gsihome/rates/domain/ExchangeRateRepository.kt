package reyst.gsihome.rates.domain

import android.support.annotation.WorkerThread

interface ExchangeRateRepository {

    @WorkerThread
    fun getCurrencies(): List<Currency>

    @WorkerThread
    fun getRate(currency: Currency): List<Rate>
}