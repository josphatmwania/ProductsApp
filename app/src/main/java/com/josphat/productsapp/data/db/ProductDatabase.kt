package com.josphat.productsapp.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.josphat.productsapp.data.db.dao.ProductDao
import com.josphat.productsapp.data.db.entities.ProductEntity
import com.josphat.productsapp.data.db.typeConverters.ProductsTypeConverters

@Database(entities = [ProductEntity::class], version = 1, exportSchema = false)
@TypeConverters(ProductsTypeConverters::class)
abstract class ProductDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao

    companion object {
        @Volatile
        private var INSTANCE: ProductDatabase? = null

        /**
         * Todo: Implemnet aSingleton pattern to get the database instance
         *
         */
        fun getDatabase(context: Context): ProductDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ProductDatabase::class.java,
                    "product_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
