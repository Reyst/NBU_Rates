package reyst.gsihome.rates.data.local

import reyst.gsihome.rates.domain.Currency
import reyst.gsihome.rates.domain.Rate

interface LocalDataSource {
    fun getCurrencies(): List<Currency>
    fun getRates(currency: Currency): List<Rate>

    fun addRate(currency: Currency, rate: Rate)
}