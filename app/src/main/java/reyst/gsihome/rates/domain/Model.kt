package reyst.gsihome.rates.domain

import java.util.*

data class Currency(
    val id: Int,
    val name: String,
    val code: String
)

data class Rate(
    val rate: Double,
    val date: Date
)