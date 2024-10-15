package com.josphat.productsapp.data.db.typeConverters

import androidx.room.TypeConverter

class ProductsTypeConverters {
    @TypeConverter
    fun convertImagesToString(images: List<String> : String) {
        return Json.encodeToString(images)
    }

    @TypeConverter
    fun convertStringToImages(images: String) : List<String> {
        return Json.decodeFromString(images)
    }
}

