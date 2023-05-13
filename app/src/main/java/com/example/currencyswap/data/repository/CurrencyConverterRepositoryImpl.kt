package com.example.currencyswap.data.repository

import android.util.Log
import com.example.currencyswap.core.Resource
import com.example.currencyswap.data.remote.services.CurrencyConverterService
import com.example.currencyswap.domain.models.Currency
import com.example.currencyswap.domain.repository.CurrencyConverterRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Classe de implementação da interface CurrencyConverterRepository, enviando os dados que a camada do Domain precisa.
 * Envia o resultado para o Domain, atuando como um mediador entre API e a aplicação.
 */

class CurrencyConverterRepositoryImpl(
    private val service: CurrencyConverterService
    ) : CurrencyConverterRepository {

    override suspend fun getConvertedCurrency(
        amount: String,
        from: String,
        to: String
    ): Flow<Resource<Currency>> = flow {

        try {
            emit(Resource.Loading())

            val result = service.getConvertedResult(amount = amount, from = from, to = to)
            emit(Resource.Success(result.toCurrency()))

        } catch (e: Exception) {
            Log.e("CurrencyRepository", "$e", )
            emit(Resource.Error("ERRO"))

        }

    }
}