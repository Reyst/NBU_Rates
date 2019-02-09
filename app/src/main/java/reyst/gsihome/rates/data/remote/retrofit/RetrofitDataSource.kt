package reyst.gsihome.rates.data.remote.retrofit

import android.annotation.SuppressLint
import reyst.gsihome.rates.data.remote.RemoteDataSource
import reyst.gsihome.rates.domain.Currency
import reyst.gsihome.rates.domain.Rate
import java.text.SimpleDateFormat

class RetrofitDataSource(private val service: RestService, pattern: String) : RemoteDataSource {

    @SuppressLint("SimpleDateFormat")
    private val df: SimpleDateFormat = SimpleDateFormat(pattern)

    override fun getRates(): Map<Currency, Rate> {
        val response = service.getRates().execute()
        return if (response.isSuccessful) {
            response.body()
                ?.currency
                ?.associate { Currency(it.id, it.name, it.code) to Rate(it.rate, df.parse(it.date)) }
                ?: emptyMap()
        } else {
            emptyMap()
        }
    }
}