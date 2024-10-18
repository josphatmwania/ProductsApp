package com.josphat.productsapp

import com.josphat.productsapp.data.ProductAPI
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import mockwebserver3.MockWebServer
import okhttp3.MediaType.Companion.toMediaType
import kotlinx.serialization.json.asConverterFactory
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.create
import java.io.IOException

class ProductsAPITest {
    private lateinit var mockWebServer: MockWebServer
    private lateinit var productAPI: ProductAPI

    @Before // This function runs before each test
    fun setUp() {
        // Set up MockWebServer for network request purposes
        mockWebServer = MockWebServer()
        mockWebServer.dispatcher = MockRequestDispatcher()
        mockWebServer.start()

        // Set up Retrofit instance
        val json = Json {
            ignoreUnknownKeys = true
            isLenient = true
        }
        val retrofit = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()

        productAPI = retrofit.create(ProductAPI::class.java)
    }

    @Test
    fun `getProductList returns a list of products`() = runTest {
//        val response = productAPI.getProductList("price")
        val response = productAPI.getProductList()
        assert(response.isSuccessful)
    }

    @After
    @Throws(IOException::class)
    fun tearDown() {
        mockWebServer.shutdown()
    }
}
