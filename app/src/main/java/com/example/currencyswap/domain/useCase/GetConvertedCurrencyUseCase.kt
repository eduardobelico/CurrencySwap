package com.example.currencyswap.domain.useCase

import com.example.currencyswap.core.Resource
import com.example.currencyswap.domain.models.Currency
import com.example.currencyswap.domain.repository.CurrencyConverterRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Classe que age como ponte entre a camada de Data e o viewModel, utilizando os dados enviados pelo repository
 * e enviando para o viewModel.
 */

class GetConvertedCurrencyUseCase(private val repository: CurrencyConverterRepository) {

    suspend operator fun invoke(
        amount: String,
        from: String,
        to: String
    ): Flow<Resource<Currency>> = repository.getConvertedCurrency(amount = amount, from = from, to = to)
}