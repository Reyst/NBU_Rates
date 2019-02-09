package reyst.gsihome.rates.data

import reyst.gsihome.rates.data.local.LocalDataSource
import reyst.gsihome.rates.data.remote.RemoteDataSource
import reyst.gsihome.rates.domain.UpdateRepository

class DefaultUpdateRepository(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
): UpdateRepository {
    override fun update() {
        remoteDataSource
            .getRates()
            .forEach { (currency, rate) -> localDataSource.addRate(currency, rate) }
    }
}