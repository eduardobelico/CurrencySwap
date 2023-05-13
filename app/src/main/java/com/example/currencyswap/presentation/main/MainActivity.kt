package com.example.currencyswap.presentation.main

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.currencyswap.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Observa o comportamento do viewModel, reagindo de acordo com os seus estados.
 */

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val viewModel by viewModel<ConverterViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        viewModelBinding()
        getAmount()
    }


    /**
     * Definir o comportamento da view de acordo com o que foi passsado
     * pelo viewModel e enviar os dados recebidos de volta.
     */

    private fun getAmount() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.conversionState.collect() { state ->

                    when (state) {
                        is ConversionState.Loading -> {
                            with(binding) {
                                activityProgressBar.isVisible = true
                                activityResult.isVisible = false
                            }
                        }
                        is ConversionState.Success -> {
                            binding.activityProgressBar.isVisible = false
                            with(binding.activityResult) {
                                isVisible = true
                                setTextColor(Color.BLACK)
                                text = state.result
                            }
                        }
                        is ConversionState.Error -> {
                            binding.activityProgressBar.isVisible = false
                            with(binding.activityResult) {
                                isVisible = false
                                setTextColor(Color.RED)
                                text = state.errorMessage
                            }
                        }
                        else -> {}
                    }
                }
            }
        }
    }

    /**
     * Função que envia os dados para o ViewModel.
     * */

    private fun viewModelBinding() {
        binding.activityButtonConvert.setOnClickListener {
            viewModel.getConvertedCurrency(
                valor = binding.activityValue.text.toString(),
                de = binding.spFromCurrency.selectedItem.toString(),
                para = binding.spToCurrency.selectedItem.toString()
            )
        }
    }


}