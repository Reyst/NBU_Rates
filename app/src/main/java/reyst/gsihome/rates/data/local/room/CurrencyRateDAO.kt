package reyst.gsihome.rates.data.local.room

import android.arch.persistence.room.*


@Dao
abstract class CurrencyRateDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(currencies: CurrencyDB)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(rate: RateDB)

    @Transaction
    open fun addRate(currency: CurrencyDB, rate: RateDB) {
        insert(currency)
        insert(rate)
    }

}