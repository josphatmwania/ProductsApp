package com.josphat.productsapp.data

import com.josphat.productsapp.common.Constants
import com.josphat.productsapp.data.model.Products
import retrofit2.http.GET



interface ProductAPI {

    @GET("products")
    suspend fun getProductList(): Products

    companion object {
        const val BASE_URL = Constants.BASE_URL
    }
}