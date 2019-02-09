package reyst.gsihome.rates.data.remote.retrofit

import retrofit2.Call
import retrofit2.http.GET

interface RestService {

    @GET("statdirectory/exchange")
    fun getRates(): Call<ResponseDTO>
}