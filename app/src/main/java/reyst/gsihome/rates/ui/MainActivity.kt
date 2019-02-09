package reyst.gsihome.rates.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import reyst.gsihome.rates.App
import reyst.gsihome.rates.R
import reyst.gsihome.rates.ui.fragments.BaseFragment
import reyst.gsihome.rates.ui.fragments.CurrencyFragment
import reyst.gsihome.rates.ui.fragments.RateFragment

class MainActivity : AppCompatActivity() {

    private var withDetails = false

    private val currencyFragment: CurrencyFragment by lazy { CurrencyFragment.newInstance() }

    private lateinit var viewModel: CurrencyRateViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        withDetails = findViewById<View>(R.id.fragment_detail) != null

        viewModel = ViewModelProviders.of(
            this,
            CurrencyRateViewModelFactory(App.instance.repository)
        ).get(CurrencyRateViewModel::class.java)

        supportFragmentManager.findFragmentById(R.id.fragment_container)
            .also {
                if (it == null || it is CurrencyFragment || withDetails) {
                    setFragmentIntoContainer(currencyFragment)
                }
            }

        if (!withDetails) {
            viewModel.getRateData().observe(this, Observer {
                if (it != null) setFragmentIntoContainer(RateFragment.newInstance())
            })
        }
    }

    private fun setFragmentIntoContainer(fragment: BaseFragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }

    override fun onBackPressed() {
        supportFragmentManager.findFragmentById(R.id.fragment_container)
            .also {
                if (it is CurrencyFragment) super.onBackPressed()
                else setFragmentIntoContainer(currencyFragment)
            }
    }
}
