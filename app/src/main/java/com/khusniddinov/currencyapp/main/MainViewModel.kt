package com.khusniddinov.currencyapp.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.khusniddinov.currencyapp.util.DispatcherProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MainViewModel @ViewModelInject constructor(
    private val repository: MainRepository,
    private val dispatchers: DispatcherProvider,
) :
    ViewModel() {

    sealed class CurrencyEvent {
        class Success(val resultText: String) : CurrencyEvent()
        class Failure(val errorText: String) : CurrencyEvent()
        object Loading : CurrencyEvent()
        object Empty : CurrencyEvent()
        private val _conversion = MutableStateFlow<CurrencyEvent>(CurrencyEvent.Empty)
        val conversion: StateFlow<CurrencyEvent> = _conversion
        fun convert(
            amountStr: String,
            fromCurrency: String,
            toCurrency: String,
        ) {
            val fromAmount = amountStr.toLongOrNull()
            if (fromAmount == null) {
                _conversion.value = CurrencyEvent.Failure("Not valid amount")
                return
            }

        }
        viewModelScope.launch(dispatchers.io){
            _conversion.value
        }
    }
}