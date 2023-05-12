package com.example.currencyswap.core

sealed class ConversionState<T> {

    object Loading: ConversionState<Nothing>()

    data class Success<T>(val result: T) : ConversionState<T>()
    data class Error(val error: Throwable) : ConversionState<Nothing>()
}
