package reyst.gsihome.rates.data

import reyst.gsihome.rates.data.local.LocalDataSource
import reyst.gsihome.rates.data.remote.RemoteDataSource
import reyst.gsihome.rates.domain.Currency
import reyst.gsihome.rates.domain.ExchangeRateRepository
import reyst.gsihome.rates.domain.Rate

class DefaultExchangeRateRepository(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) : ExchangeRateRepository {

    override fun getCurrencies(): List<Currency> {
        return localDataSource.getCurrencies().let {
            if (it.isNotEmpty()) it
            else remoteDataSource.getRates()
                .entries
                .map { entry ->  entry.key }
        }

    }

    override fun getRate(currency: Currency): List<Rate> {

        return localDataSource.getRates(currency).let {
            if (it.isNotEmpty()) it
            else remoteDataSource.getRates()
                .entries
                .filter { entry -> entry.key.id == currency.id }
                .map { entry ->  entry.value }
        }


    }


}