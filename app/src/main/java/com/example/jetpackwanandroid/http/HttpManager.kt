package com.example.jetpackwanandroid.http

import com.example.jetpackwanandroid.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Author: Sbingo666
 * Date:   2019/4/3
 */
class HttpManager private constructor() {

    companion object {
        @Volatile
        private var instance: HttpManager? = null

        fun getInstance(): HttpManager {
            return instance ?: synchronized(this) {
                instance ?: HttpManager().also { instance = it }
            }
        }
    }

    internal val wanApi: WanApi by lazy {
        create(Constants.BASE_URL, WanApi::class.java)
    }

    private fun <T> create(baseUrl: String, c: Class<T>): T {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(provideOKHttpClient())
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(c)
    }


    private fun provideOKHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()
            .connectTimeout(Constants.NETWORK_TIME.toLong(), TimeUnit.SECONDS)
            .readTimeout(Constants.NETWORK_TIME.toLong(), TimeUnit.SECONDS)
            .writeTimeout(Constants.NETWORK_TIME.toLong(), TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
        return builder.build()
    }
}
