package com.josphat.productsapp

import android.content.res.Resources
import mockwebserver3.Dispatcher
import mockwebserver3.MockResponse
import mockwebserver3.RecordedRequest
import java.io.File
import java.lang.IllegalArgumentException

class MockRequestDispatcher : Dispatcher() {
    override fun dispatch(request: RecordedRequest): MockResponse {
        return when (request.path) {
            "/products?price=2.49" -> {
                MockResponse()
                    .setResponseCode(HttpURLConnection.HTTP_OK)
                    .setBody(getJson("productsresponse.json"))
            }
            else -> throw IllegalArgumentException("Unkown Request Path ${request.path}")
        }

    }

    /**
     * create a getJson function that takes a path and returns a String instance type
     *
     */
    private fun getJson(path: String): String {
        val uri = Resources.getResource(path)
        val file = File(uri.path)
        return String(file.readBytes())
    }

}