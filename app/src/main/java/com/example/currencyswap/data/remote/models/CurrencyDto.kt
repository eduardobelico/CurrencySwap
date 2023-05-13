package com.example.currencyswap.data.remote.models

import com.example.currencyswap.domain.models.Currency

/**
 * Modelo da camada de Data para tratar quaiquer mudanças que venham da API.
 * Função de conversão para a camada do Domain.
 */

data class CurrencyDto(
    val result: String
) {

    fun toCurrency() = Currency(
        result = result
    )
}
