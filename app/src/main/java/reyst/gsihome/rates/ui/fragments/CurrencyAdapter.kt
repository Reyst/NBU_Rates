package reyst.gsihome.rates.ui.fragments

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import reyst.gsihome.rates.R
import reyst.gsihome.rates.domain.Currency

class CurrencyAdapter(private val onItemClick: (Currency) -> Unit) : RecyclerView.Adapter<CurrencyVH>() {

    private val items: MutableList<Currency> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, type: Int): CurrencyVH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_currency, parent, false)
        return CurrencyVH(view, onItemClick)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: CurrencyVH, position: Int) {
        holder.bind(items[position])
    }

    fun setItems(newItems: Collection<Currency>?) {
        items.clear()
        newItems?.also { items.addAll(it) }
        notifyDataSetChanged()
    }
}

class CurrencyVH(itemView: View, private val onItemClick: (Currency) -> Unit) : RecyclerView.ViewHolder(itemView) {

    private val tvCode = itemView.findViewById<TextView>(R.id.tv_string_code)
    private val tvName = itemView.findViewById<TextView>(R.id.tv_name)

    fun bind(currency: Currency) {
        tvCode.text = currency.code.toUpperCase()
        tvName.text = currency.name
        itemView.setOnClickListener { onItemClick(currency) }
    }
}
