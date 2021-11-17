package com.application.data.net


import com.ascend.mobile.exam.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitUtils {



    @Synchronized
    fun setupRetrofit(): Retrofit {
        val mBaseUrl = BuildConfig.WS_URL
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(GsonUtils.build()))
            .baseUrl(mBaseUrl)
            .client(createOkHttpClient())
            .build()

    }

    private fun createOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()
    }


}