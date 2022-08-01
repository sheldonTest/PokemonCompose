package com.example.pokemoncompose.business.network

import okhttp3.Interceptor
import okhttp3.Response
import timber.log.Timber

class RequestInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val initialRequest = chain.request()
        val request = initialRequest.newBuilder().url(initialRequest.url()).build()
        Timber.d("$$$$$$$$ Interceptor Request: $request $$$$$$$$$$")
        return chain.proceed(request)
    }
}