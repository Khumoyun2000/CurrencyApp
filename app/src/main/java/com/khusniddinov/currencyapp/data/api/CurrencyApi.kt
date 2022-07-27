package com.khusniddinov.currencyapp.data.api

import com.khusniddinov.currencyapp.data.models.GetCurrencyItem
import retrofit2.Response
import retrofit2.http.GET

interface CurrencyApi {
@GET("/gson")
suspend fun getRates():Response<GetCurrencyItem>



}