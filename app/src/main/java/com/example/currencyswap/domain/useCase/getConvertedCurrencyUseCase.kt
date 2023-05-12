package com.example.currencyswap.domain.useCase

import com.example.currencyswap.core.ConversionState
import com.example.currencyswap.domain.models.Currency
import com.example.currencyswap.domain.repository.CurrencyConverterRepository
import kotlinx.coroutines.flow.Flow


class getConvertedCurrencyUseCase(private val repository: CurrencyConverterRepository) {

    operator fun invoke(
        amount: String,
        from: String,
        to: String
    ): Flow<Currency> = repository.getConvertedCurrency(amount: String, from: String, to: String)
}