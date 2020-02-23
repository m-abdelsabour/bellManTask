package com.bellman.task.domain.gateways

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

const val BASE_URL = "http://bellman-bd.restart-technology.com/api/"

val retrofit: Retrofit by lazy {
    Retrofit.Builder().baseUrl(BASE_URL)
        .client(okHttpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
}


fun okHttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor())
        .addInterceptor {
            val originalRequest = it.request()
            val builder = originalRequest.newBuilder()
            builder.addHeader("Accept", "application/json")
            builder.addHeader("Content-Type", "application/json")
            val newRequest = builder.build()
            it.proceed(newRequest)
        }
        .readTimeout(60, TimeUnit.SECONDS)
        .connectTimeout(60, TimeUnit.SECONDS)
        .build()
}

fun loggingInterceptor(): HttpLoggingInterceptor {
    val loggingInterceptor = HttpLoggingInterceptor()
    loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
    return loggingInterceptor

}