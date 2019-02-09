package reyst.gsihome.rates.data.local.room

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query


@Dao
abstract class RateDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(rates: List<RateDB>)

    @Query("SELECT * FROM exchange WHERE currency_id = :id ORDER BY exchange_date DESC")
    abstract fun getRatesByCurrencyId(id: Int): List<RateDB>

}