package reyst.gsihome.rates.ui.fragments

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import reyst.gsihome.rates.R

class CurrencyFragment : BaseFragment() {

    companion object {
        fun newInstance() = CurrencyFragment()
    }

    private val currencyAdapter = CurrencyAdapter { currency -> viewModel.getRatesFor(currency) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.currency_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.findViewById<RecyclerView>(R.id.rv_currency)
            ?.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = currencyAdapter
            }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.getCurrencyData().observe(this, Observer { currencyAdapter.setItems(it)} )
    }

}
