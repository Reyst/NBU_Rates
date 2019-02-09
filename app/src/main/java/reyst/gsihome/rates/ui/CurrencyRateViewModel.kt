package reyst.gsihome.rates.ui

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import kotlinx.coroutines.*
import reyst.gsihome.rates.domain.Currency
import reyst.gsihome.rates.domain.ExchangeRateRepository
import reyst.gsihome.rates.domain.Rate


class CurrencyRateViewModel(private val repository: ExchangeRateRepository) : ViewModel() {

    private val rateData: MutableLiveData<Pair<Int, List<Rate>>> = MutableLiveData()
    private val currencyData: MutableLiveData<List<Currency>> = MutableLiveData()

    fun getRateData(): LiveData<Pair<Int, List<Rate>>> = rateData
    fun getCurrencyData(): LiveData<List<Currency>> {
        if (currencyData.value == null)
            loadCurrencyInfo()

        return currencyData
    }

    private fun loadCurrencyInfo() {
        GlobalScope.launch {
            withContext(Dispatchers.IO) { async { repository.getCurrencies() } }
                .await()
                .let { currencyData.postValue(it) }
        }
    }

    fun getRatesFor(currency: Currency) {
        rateData.postValue(null)
        GlobalScope.launch {
            withContext(Dispatchers.IO) { async { repository.getRate(currency) } }
                .await()
                .let { rateData.postValue(currency.id to it) }
        }
    }
}


class CurrencyRateViewModelFactory(
    private val repository: ExchangeRateRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass == CurrencyRateViewModel::class.java)
            return CurrencyRateViewModel(repository) as T
        else
            throw RuntimeException("Cannot create an instance of $modelClass")
    }
}