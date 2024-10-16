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
 * Pass an instance of the the API to access it & inherit from the Products repository interface
 */
class ProductsRepositoryImpl (
    private val productAPI: ProductAPI,
    private val productDao: ProductDao // Provide access to the DB for storage and retrival
) : ProductsRepository {
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override suspend fun getProductList(): Flow<Result<List<Product>>> {
        return flow {
            val productsFromAPI = try {
                // Get the product list when there's no error

                productAPI.getProductList()

            } catch (e: IOException) {
                e.printStackTrace()
                emit(Result.Error(message = "Ooops! Error Loading Products!"))
                return@flow
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Result.Error(message = "Ooops! Error Loading Products!"))
                return@flow

            } catch (e: Exception) {  // Catching general exceptions
                e.printStackTrace()
                emit(Result.Error(message = "Ooops! Error Loading Products!"))
                return@flow

                /**
                 * Todo: save fetched products to the database
                 */

                productsFromAPI.products.forEach{ product ->
                    productDao.insert(
                        ProductEntity(
                            id = product.id,
                            brand = product.brand,
                            description = product.description,
                            discountPercentage = product.discountPercentage,
                            images = product.images,
                            price = product.price,
                            rating = product.rating,
                            stock = product.stock,
                            thumbnail = product.thumbnail,
                            title = product.title
                        )
                    )

                }






            }
            emit(Result.Success(productsFromAPI.products))
        }


    }

    // Todo: To fetch products from the local database

    suspend fun getProductsFromDB() : Flow<List<ProductEntity>> {
        return productDao.getProducts()
    }
}