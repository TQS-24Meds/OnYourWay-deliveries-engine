package com.example.onyourway

import okhttp3.OkHttpClient.Builder
import retrofit2.Retrofit
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.OkHttpClient
import retrofit2.converter.gson.GsonConverterFactory
import com.example.onyourway.RiderInterface
import com.example.onyourway.APIClient

object APIClient {
    val retrofit: Retrofit
        get() {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            val okHttpClient: OkHttpClient =
                Builder().addInterceptor(httpLoggingInterceptor).build()
            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://192.168.160.238:6767/api/")
                .client(okHttpClient)
                .build()
        }
    val service: RiderInterface
        get() = retrofit.create(
            RiderInterface::class.java
        )
}