package com.example.currencyswap.data.remote.models

import com.example.currencyswap.domain.models.Currency

data class CurrencyDto(
    val result: String
) {

    fun toCurrency() = Currency(
        result = result
    )
}
