package com.josphat.productsapp.data

import com.josphat.productsapp.data.model.Products
import kotlinx.coroutines.flow.Flow

interface ProductsRepository {

//    Todo: get Products in a list form
    suspend fun getProductList() : Flow<Result<List<Products>>>
}