package reyst.gsihome.rates.data.local.room

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import reyst.gsihome.rates.domain.Currency

@Entity(tableName = "currencies")
data class CurrencyDB(
    @PrimaryKey
    val id: Int,
    val name: String,
    val code: String
)

fun CurrencyDB.toDomain() = Currency(id, name, code)
fun Collection<CurrencyDB>.toDomain() = map { it.toDomain() }

fun Currency.toDBObject() = CurrencyDB(id, name, code)
//fun Collection<Currency>.toDBObject() = map { it.toDBObject() }
