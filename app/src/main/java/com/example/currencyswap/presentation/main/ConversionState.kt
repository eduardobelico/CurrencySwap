package com.example.currencyswap.presentation.main

/**
 * Classe de controle dos estados da View.
 */

sealed class ConversionState {

        object Loading : ConversionState()
        object Empty : ConversionState()
        data class Success(val result: String) : ConversionState()
        data class Error(val errorMessage: String) : ConversionState()

}
