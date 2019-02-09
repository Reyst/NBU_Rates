package reyst.gsihome.rates.data.local.room

import reyst.gsihome.rates.data.local.LocalDataSource
import reyst.gsihome.rates.domain.Currency
import reyst.gsihome.rates.domain.Rate

class RoomDataSource(private val db: AppDatabase) : LocalDataSource {

    override fun getRates(currency: Currency): List<Rate> {
        return db.rateDao().getRatesByCurrencyId(currency.id).toDomain()
    }

    override fun getCurrencies(): List<Currency> {
        return db.currencyDao().getCurrencies().toDomain()
    }

    override fun addRate(currency: Currency, rate: Rate) {
        db.currencyRateDao().addRate(currency.toDBObject(), rate.toDBObject(currency))
    }

}