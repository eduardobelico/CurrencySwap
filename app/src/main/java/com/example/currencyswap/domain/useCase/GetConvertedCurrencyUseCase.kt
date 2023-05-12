package com.example.currencyswap.domain.useCase

import com.example.currencyswap.domain.models.Currency
import com.example.currencyswap.domain.repository.CurrencyConverterRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class GetConvertedCurrencyUseCase(private val repository: CurrencyConverterRepository) {

    operator fun invoke(
        amount: String,
        from: String,
        to: String
    ): Flow<Currency> = flow {
        repository.getConvertedCurrency(amount = amount, from = from, to = to)
    }
}