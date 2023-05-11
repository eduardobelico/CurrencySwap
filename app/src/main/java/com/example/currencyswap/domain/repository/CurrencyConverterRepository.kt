package com.example.currencyswap.domain.repository

import com.example.currencyswap.domain.models.Currency

interface CurrencyConverterRepository {

    suspend fun getConvertedCurrency(amount: String, from: String, to: String) : Currency
}