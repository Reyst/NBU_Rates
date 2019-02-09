package reyst.gsihome.rates.ui.fragments

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import reyst.gsihome.rates.R

class RateFragment : BaseFragment() {

    companion object {
        fun newInstance() = RateFragment()
    }

    private lateinit var recycler: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.rate_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recycler = view.findViewById(R.id.rv_rates)
        recycler.layoutManager = LinearLayoutManager(context)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.getRateData().observe(this, Observer {data ->
            val (code, items) = data ?: Pair(-1, listOf())
            recycler.adapter = RateAdapter(code, items)
        })
    }
}
