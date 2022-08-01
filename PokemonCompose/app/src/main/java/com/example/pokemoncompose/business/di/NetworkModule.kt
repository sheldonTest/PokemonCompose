package com.example.pokemoncompose.business.di

import com.example.pokemoncompose.business.network.PokeApi
import com.example.pokemoncompose.business.network.PokeApiService
import com.example.pokemoncompose.business.network.RequestInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

/**
 * Hilt Network module for making
 * initial API networking calls
 * and provide middleware service
 * object - PokeApiService
 */
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideOkHttpClient() : OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(RequestInterceptor()).build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient) : PokeApiService {
        return Retrofit.Builder()
            .client(client).baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build().create(PokeApiService::class.java)
    }

    @Provides
    @Singleton
    fun providePokeApi(service: PokeApiService) : PokeApi {
        return PokeApi(service)
    }


}