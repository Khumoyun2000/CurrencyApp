package com.khusniddinov.currencyapp.di

import com.khusniddinov.currencyapp.data.api.CurrencyApi
import com.khusniddinov.currencyapp.main.DefaultMainRepository
import com.khusniddinov.currencyapp.main.MainRepository
import com.khusniddinov.currencyapp.util.DispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

private const val BASE_URL = "https://nbu.uz/uz/exchange-rates/"

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideCurrencyApi(): CurrencyApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(CurrencyApi::class.java)


    @Singleton
    @Provides
    fun provideMainRepository(api: CurrencyApi): MainRepository = DefaultMainRepository(api)
@Singleton
@Provides
fun provideDispatchers():DispatcherProvider=object :DispatcherProvider{
    override val main: CoroutineDispatcher
        get() = Dispatchers.Main
    override val io: CoroutineDispatcher
        get() =Dispatchers.IO
    override val default: CoroutineDispatcher
        get() =Dispatchers.Default
    override val unconfined: CoroutineDispatcher
        get() =Dispatchers.Unconfined
}
}