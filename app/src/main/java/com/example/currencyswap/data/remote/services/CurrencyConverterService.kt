package com.example.currencyswap.data.remote.services

import com.example.currencyswap.core.Constants.API_KEY
import com.example.currencyswap.data.remote.models.CurrencyDto
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface CurrencyConverterService {

    @GET("convert")
    suspend fun getConvertedResult(
        @Header("apikey") key: String = API_KEY,
        @Query("amount") amount: String,
        @Query("from") from: String,
        @Query("to") to: String
    ): CurrencyDto
}