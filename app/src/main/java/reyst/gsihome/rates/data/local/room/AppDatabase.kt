package reyst.gsihome.rates.data.local.room

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters

@Database(entities = [CurrencyDB::class, RateDB::class], version = 1)
@TypeConverters(DateConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun currencyDao(): CurrencyDAO

    abstract fun rateDao(): RateDAO

    abstract fun currencyRateDao(): CurrencyRateDAO

}