package com.khusniddinov.currencyapp.main

import com.khusniddinov.currencyapp.data.api.CurrencyApi
import com.khusniddinov.currencyapp.data.models.GetCurrencyItem
import com.khusniddinov.currencyapp.util.Resource

interface MainRepository {
    suspend fun getRates():Resource<GetCurrencyItem>
}