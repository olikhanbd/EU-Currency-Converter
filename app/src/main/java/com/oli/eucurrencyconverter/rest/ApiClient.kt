package com.oli.eucurrencyconverter.rest

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    private const val BASE_URL = "https://jsonvat.com/"

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor{chain ->
            val original = chain.request()

            val requestBuilder = original.newBuilder()
                .addHeader("Authorization", "")
                .method(original.method(), original.body())

            val request = requestBuilder.build()
            chain.proceed(request)
    }.build()

    val instance: ApiInterface by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

        retrofit.create(ApiInterface::class.java)

    }
}