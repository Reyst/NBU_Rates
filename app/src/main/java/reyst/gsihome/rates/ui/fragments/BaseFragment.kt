package reyst.gsihome.rates.ui.fragments

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import reyst.gsihome.rates.App
import reyst.gsihome.rates.ui.CurrencyRateViewModel
import reyst.gsihome.rates.ui.CurrencyRateViewModelFactory

abstract class BaseFragment : Fragment() {

    protected lateinit var viewModel: CurrencyRateViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(
            activity as AppCompatActivity,
            CurrencyRateViewModelFactory(App.instance.repository)
        ).get(CurrencyRateViewModel::class.java)
    }
}