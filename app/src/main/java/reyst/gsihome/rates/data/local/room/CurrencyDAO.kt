package reyst.gsihome.rates.data.local.room

import android.arch.persistence.room.*


@Dao
abstract class CurrencyDAO {

    @Query("SELECT * FROM currencies")
    abstract fun getCurrencies(): List<CurrencyDB>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(currencies: List<CurrencyDB>)

}