package com.example.currencyswap.presentation.main

sealed class ConversionState {

        object Loading : ConversionState()
        data class Success(val result: String) : ConversionState()
        data class Error(val errorMessage: String) : ConversionState()

}
