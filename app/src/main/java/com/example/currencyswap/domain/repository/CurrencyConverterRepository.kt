package com.example.currencyswap.domain.repository

import com.example.currencyswap.core.Resource
import com.example.currencyswap.domain.models.Currency
import kotlinx.coroutines.flow.Flow

/**
 * Interface que abstrai a implementação do repositório para os objetos do tipo Currency.
 */

interface CurrencyConverterRepository {

    suspend fun getConvertedCurrency(amount: String, from: String, to: String) : Flow<Resource<Currency>>
}