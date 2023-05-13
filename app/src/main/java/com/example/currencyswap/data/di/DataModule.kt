package com.example.currencyswap.data.di

import android.util.Log
import com.example.currencyswap.core.Constants.BASE_URL
import com.example.currencyswap.core.Constants.OK_HTTP
import com.example.currencyswap.data.remote.services.CurrencyConverterService
import com.example.currencyswap.data.repository.CurrencyConverterRepositoryImpl
import com.example.currencyswap.domain.repository.CurrencyConverterRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Organiza as dependências da camada Data e cria a função para carregar os módulos na classe App.
 */

object DataModule {
    fun load() {
        loadKoinModules(convertionModule() + networkModule())
    }

    private fun convertionModule() : Module {
        return module {
            single<CurrencyConverterRepository> {CurrencyConverterRepositoryImpl(get())
            }
        }
    }

    private fun networkModule(): Module {
        return module {
            single<CurrencyConverterService> { createService(get(), get()) }

            single {
                Moshi.Builder()
                    .add(KotlinJsonAdapterFactory())
                    .build()
            }

            single {
                val interceptor = HttpLoggingInterceptor {
                    Log.e(OK_HTTP, it)
                }
                interceptor.level = HttpLoggingInterceptor.Level.BODY
                OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .build()
            }
        }
    }

    private inline fun <reified T> createService(
        factory: Moshi,
        client: OkHttpClient
    ) : T {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(factory))
            .client(client)
            .build()
            .create(T::class.java)
    }

}