package com.example.currencyswap.presentation.di

import com.example.currencyswap.presentation.main.ConverterViewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

/**
 * Organiza as dependências da camada Presentation e cria a função para carregar os módulos na classe App.
 */

object PresentationModule {

    fun load() {
        loadKoinModules(viewModelModule())
    }

    private fun viewModelModule(): Module {

        return module {
            factory { ConverterViewModel(convertedCurrencyUseCase = get()) }
        }
    }
}