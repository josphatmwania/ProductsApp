package com.josphat.productsapp.data

import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
import com.josphat.productsapp.data.db.dao.ProductDao
import com.josphat.productsapp.data.db.entities.ProductEntity
import com.josphat.productsapp.data.model.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

/**
 * Pass an instance of the API to access it & inherit from the Products repository interface
 */
class ProductsRepositoryImpl(
    private val productAPI: ProductAPI,
    private val productDao: ProductDao // Provide access to the DB for storage and retrieval
) : ProductsRepository {
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override suspend fun getProductList(): Flow<Result<List<Product>>> {
        return flow {
            val productsFromAPI: ProductAPI

            try {
                // Get the product list when there's no error
                productsFromAPI = productAPI.getProductList() // Assign fetched data here
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Result.Error(message = "Ooops! Error Loading Products!"))
                return@flow
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Result.Error(message = "Ooops! Error Loading Products!"))
                return@flow
            } catch (e: Exception) { // Catching general exceptions
                e.printStackTrace()
                emit(Result.Error(message = "Ooops! Error Loading Products!"))
                return@flow
            }

            // Save fetched products to the database
            productsFromAPI.products.forEach { product ->
                productDao.insert(
                    ProductEntity(
                        id = product.id,
                        brand = product.brand ?: "Unknown Brand", // Default value if null
                        description = product.description ?: "No Description", // Default value if null
                        discountPercentage = product.discountPercentage ?: 0.0, // Default value if null
                        images = product.images ?: emptyList(), // Default to an empty list if null
                        price = product.price ?: 0.0, // Default value if null
                        rating = product.rating ?: 0.0, // Default value if null
                        stock = product.stock ?: 0, // Default value if null
                        thumbnail = product.thumbnail ?: "", // Default value if null
                        title = product.title ?: "No Title" // Default value if null
                    )
                )
            }

            emit(Result.Success(productsFromAPI.products))
        }
    }

    // Todo: To fetch products from the local database
    override suspend fun getProductsFromDB(): Flow<List<ProductEntity>> {
        return productDao.getProducts()
    }
}
