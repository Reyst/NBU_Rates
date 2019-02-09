package reyst.gsihome.rates.ui.fragments

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import reyst.gsihome.rates.R
import reyst.gsihome.rates.domain.Rate
import java.text.DateFormat

class RateAdapter(private val code: Int, private val items: List<Rate>) : RecyclerView.Adapter<RateVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, type: Int): RateVH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_rate, parent, false)
        return RateVH(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RateVH, position: Int) {
        holder.bind(code, items[position])
    }
}

class RateVH(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val tvCode = itemView.findViewById<TextView>(R.id.tv_code)
    private val tvDate = itemView.findViewById<TextView>(R.id.tv_date)
    private val tvRate = itemView.findViewById<TextView>(R.id.tv_rate)

    fun bind(code: Int, rate: Rate) {
        val context = itemView.context

        tvCode.text = code.toString()
        tvDate.text = DateFormat.getDateInstance().format(rate.date)
        tvRate.text = context.getString(R.string.rate_format, rate.rate.toFloat())
    }
}
