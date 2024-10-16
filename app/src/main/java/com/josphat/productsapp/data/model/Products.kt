package com.josphat.productsapp.data.model

import com.josphat.productsapp.data.ProductAPI

data class Products(
    val limit: Int,
    val products: List<Product>,
    val skip: Int,
    val total: Int
) : ProductAPI {
    override suspend fun getProductList(): Products {
        TODO("Not yet implemented")
    }
}