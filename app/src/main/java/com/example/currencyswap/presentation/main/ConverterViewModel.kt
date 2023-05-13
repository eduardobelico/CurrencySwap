package com.example.currencyswap.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.currencyswap.core.Resource
import com.example.currencyswap.domain.useCase.GetConvertedCurrencyUseCase
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlin.math.round

/**
 *
 */


class ConverterViewModel(private val convertedCurrencyUseCase: GetConvertedCurrencyUseCase) : ViewModel() {

    private val _conversionState = MutableStateFlow<ConversionState>(ConversionState.Empty)
    val conversionState: StateFlow<ConversionState> = _conversionState

    /**
     * Função que faz a conversão a partir da API.
     * */

    fun getConvertedCurrency(
        valor: String?,
        de: String,
        para: String
    ) {
        /**
         * Verificação de segurança para garantir que o campo "Amount" sesteja preenchido..
         * */

        if (valor.isNullOrBlank()) {
            _conversionState.value = ConversionState.Error("Insira um valor")
            return
        }

        /**
         * Modificação do estado da variável para ser observada pela View.
         */

        viewModelScope.launch(IO) {

            convertedCurrencyUseCase(
                amount = valor,
                from = de,
                to = para
            ).collect { result ->
                when(result) {

                    is Resource.Success -> {

                        val convertedCurrency = result.data?.let { currency ->
                            round(currency.result.toFloat() * 100) /100

                        }

                        _conversionState.value =
                            ConversionState.Success(
                                "$valor $de = $convertedCurrency $para"
                            )
                    }
                    is Resource.Error -> {

                        _conversionState.value =
                            ConversionState.Error(
                                result.message ?: "Conversão não concluída. Tente novamente!"
                            )
                    }
                    is Resource.Loading -> {

                        _conversionState.value = ConversionState.Loading
                    }
                }
            }
        }
    }
}
