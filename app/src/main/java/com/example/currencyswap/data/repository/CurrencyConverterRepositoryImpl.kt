package com.example.currencyswap.data.repository

import com.example.currencyswap.data.remote.services.CurrencyConverterService
import com.example.currencyswap.domain.models.Currency
import com.example.currencyswap.domain.repository.CurrencyConverterRepository

class CurrencyConverterRepositoryImpl(private val service: CurrencyConverterService) : CurrencyConverterRepository {

    override suspend fun getConvertedCurrency(
        amount: String,
        from: String,
        to: String
    ): Currency {
        return service.getConvertedResult(
            amount = amount,
            from = from,
            to = to
        ).toCurrency()
    }
}