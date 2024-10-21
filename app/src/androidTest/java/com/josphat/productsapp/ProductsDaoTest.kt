package com.josphat.productsapp

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.josphat.productsapp.data.db.ProductDatabase
import com.josphat.productsapp.data.db.dao.ProductDao
import org.junit.After
import org.junit.Before
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
}