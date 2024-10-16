package com.josphat.productsapp.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.josphat.productsapp.data.db.entities.ProductEntity
import kotlinx.coroutines.flow.Flow
/**
 * A DAO is an interface that defines the methods to create, read, update, and delete
 * values from our database.
 */

@Dao
interface ProductDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(productEntity: ProductEntity)
    @Query("SELECT * FROM products")
    fun getProducts() : Flow<List<ProductEntity>>

}