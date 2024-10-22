package com.josphat.productsapp

import mockwebserver3.Dispatcher
import mockwebserver3.MockResponse
import mockwebserver3.RecordedRequest
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.IllegalArgumentException

class MockRequestDispatcher : Dispatcher() {
    override fun dispatch(request: RecordedRequest): MockResponse {
        return when (request.path) {
            "/products?price=2.49" -> {
                MockResponse()
                    .setResponseCode(200) // HTTP_OK
                    .setBody(getJson("productsresponse.json"))
            }
            else -> throw IllegalArgumentException("Unknown Request Path: ${request.path}")
        }
    }

    /**
     * Create a getJson function that takes a file name and returns its content as a String.
     */
    private fun getJson(fileName: String): String {
        val inputStream = this::class.java.classLoader?.getResourceAsStream(fileName)
            ?: throw IllegalArgumentException("File not found: $fileName")
        return BufferedReader(InputStreamReader(inputStream)).use { it.readText() }
    }
}
