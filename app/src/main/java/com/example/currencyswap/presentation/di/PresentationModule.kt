package com.example.currencyswap.presentation.di

import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

object PresentationModule {

    fun load() {
        loadKoinModules(viewModelModule())
    }

    private fun viewModelModule(): Module {

        /**
         * Aqui estamos pegando a dependencia do UseCase
         * lá do Domain.di.
         * */

        return module {
            factory { ConvertViewModel(xxx = get()) }
        }
}