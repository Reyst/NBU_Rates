package reyst.gsihome.rates.data.remote

import reyst.gsihome.rates.domain.Currency
import reyst.gsihome.rates.domain.Rate

interface RemoteDataSource {
    fun getRates(): Map<Currency, Rate>
}