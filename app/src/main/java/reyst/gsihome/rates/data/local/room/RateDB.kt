package reyst.gsihome.rates.data.local.room

import android.arch.persistence.room.*
import reyst.gsihome.rates.domain.Currency
import reyst.gsihome.rates.domain.Rate
import java.util.*

@Entity(
    tableName = "exchange",
    foreignKeys = [ForeignKey(
        entity = CurrencyDB::class,
        parentColumns = ["id"],
        childColumns = ["currency_id"],
        onDelete = ForeignKey.CASCADE
    )],
    primaryKeys = ["currency_id", "exchange_date"]
)
data class RateDB(

    @ColumnInfo(name = "exchange_date")
    val date: Date,

    @ColumnInfo(name = "exchange_rate")
    val rate: Double,

    @ColumnInfo(name = "currency_id")
    val currencyId: Int
)

class DateConverter {
    companion object {
        @TypeConverter
        @JvmStatic
        fun timestampToDate(timestamp: Long) = timestamp.let { if (it != 0L) Date(it) else null }

        @TypeConverter
        @JvmStatic
        fun dateToTimestamp(date: Date?) = date?.time ?: 0L
    }
}

fun RateDB.toDomain() = Rate(rate, date)
fun Collection<RateDB>.toDomain() = map { it.toDomain() }

fun Rate.toDBObject(currency: Currency) = RateDB(date, rate, currency.id)
//fun Collection<Rate>.toDBObject(currency: Currency) = map { it.toDBObject(currency) }
