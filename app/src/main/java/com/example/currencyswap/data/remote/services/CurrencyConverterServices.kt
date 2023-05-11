package com.example.currencyswap.data.remote.services

import retrofit2.http.GET

interface CurrencyConverterServices {

    @GET("convert")
    suspend fun getConvertedCurrency(

    )
}