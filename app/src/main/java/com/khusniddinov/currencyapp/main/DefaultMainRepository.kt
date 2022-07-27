package com.khusniddinov.currencyapp.main

import com.khusniddinov.currencyapp.data.api.CurrencyApi
import com.khusniddinov.currencyapp.data.models.GetCurrencyItem
import com.khusniddinov.currencyapp.util.Resource
import java.lang.reflect.Executable
import javax.inject.Inject

class DefaultMainRepository @Inject constructor(
    private val api:CurrencyApi
):MainRepository {
    override suspend fun getRates(): Resource<GetCurrencyItem> {
        return try {
val response=api.getRates()
            val result=response.body()
            if (response.isSuccessful && result!=null){
                Resource.Success(result)
            }
            else{
                Resource.Error(response.message())
            }
        }
        catch (e:Exception){
Resource.Error(e.message?:"An error occured")
        }
    }
}