package com.example.currencyswap.domain.di

import com.example.currencyswap.domain.useCase.GetConvertedCurrencyUseCase
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

/**
 * Organiza as dependências da camada Domain e cria a função para carregar os módulos na classe App.
 */

object DomainModule {
    fun load() {
        loadKoinModules(useCaseModule())
    }

    private fun useCaseModule(): Module {
        return module {
            factory { GetConvertedCurrencyUseCase(repository = get())}
        }
    }
}