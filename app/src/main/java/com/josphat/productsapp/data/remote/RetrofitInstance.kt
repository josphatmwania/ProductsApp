package com.josphat.productsapp.data.remote

import com.josphat.productsapp.data.ProductAPI
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Create an instance of the API
 *
 */
object RetrofitInstance {
    // HTTP logg in interceptor
    private val interceptor:HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()

    val productAPI : ProductAPI = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(productAPI.BASE_URL)
        .client(client)
        .build()
        .create(productAPI::class.java)




}