package com.example.currencyswap.data.remote.dtos

import com.example.currencyswap.domain.models.Currency

data class CurrencyDto(
    val result: String
) {

    fun toCurrency() = Currency(
        result = result
    )
}
