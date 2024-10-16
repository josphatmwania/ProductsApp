package com.josphat.productsapp.data

import com.josphat.productsapp.data.db.entities.ProductEntity
import com.josphat.productsapp.data.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductsRepository {

//    Todo: get Products in a list form
    suspend fun getProductList() : Flow<Result<List<Product>>>
    suspend fun getProductsFromDB(): Flow<List<ProductEntity>> //     //Todo: Fetch products stored in the local database

}