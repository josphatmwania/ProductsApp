package com.josphat.productsapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.josphat.productsapp.data.db.dao.ProductDao
import com.josphat.productsapp.data.db.entities.ProductEntity
import com.josphat.productsapp.data.db.typeConverters.ProductsTypeConverters


@Database(
    entities = [ProductEntity::class],
    version = 1
)

@TypeConverters(ProductsTypeConverters::class)
abstract class ProductDatabase: RoomDatabase() {
    abstract fun productDao() : ProductDao
}