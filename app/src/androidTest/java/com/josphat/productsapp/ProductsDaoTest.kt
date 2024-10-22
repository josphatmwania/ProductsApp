package com.josphat.productsapp

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.josphat.productsapp.data.db.ProductDatabase
import com.josphat.productsapp.data.db.dao.ProductDao
import com.josphat.productsapp.data.db.entities.ProductEntity
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class ProductsDaoTest {
    private lateinit var database: ProductDatabase
    private lateinit var productDao: ProductDao
    @Before
    fun createDatabase() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            ProductDatabase::class.java
        ).allowMainThreadQueries().build()
        productDao = database.productDao()
    }
    @After
    fun closeDatabase() {
        database.close()
    }


    //Tests
    @Test
    fun testInsertAndReadProducts() = runTest {

        // Given Product
        val product = ProductEntity(
            id = 28,
            brand = "Chanel",
            description = "A very beautiful Bag",
            discountPercentage = 18.9,
            // Assuming images is a list of strings
            images = listOf("image1.jpg", "image2.jpg"),
            price = 10.87,
            rating = 28.9,
            stock = 21,
            thumbnail = "Not added",
            title = "Blue Bag"

        )
        productDao.insert(product)
        val products = productDao.getProducts()
        assert(products.first().contains(product))

    }

}