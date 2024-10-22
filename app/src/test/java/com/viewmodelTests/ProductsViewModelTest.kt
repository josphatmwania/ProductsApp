package com.viewmodelTests

import com.josphat.productsapp.data.ProductsRepository
import com.josphat.productsapp.data.model.Product
import com.josphat.productsapp.presentation.viewmodel.ProductsViewModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.Assert.assertEquals

class ProductsViewModelTest {
    private val productsRepository = mockk<ProductsRepository>(relaxed = true) // Provide a mock instance of ProductsRepository
    private lateinit var productsViewModel: ProductsViewModel

    @Before
    fun setUp() { // Run before our tests
        Dispatchers.setMain(Dispatchers.Unconfined)
        productsViewModel = ProductsViewModel(productsRepository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    // Tests
    @Test
    fun testGetProducts() = runTest {
        val products = listOf(
            Product(
                id = 28,
                brand = "Chanel",
                description = "A very beautiful Bag",
                discountPercentage = 18.9,
                images = listOf("image1.jpg", "image2.jpg"),
                price = 10.87,
                rating = 28.9,
                stock = 21,
                thumbnail = "Not added",
                title = "Blue Bag",
                category = "Bag"
            )
        )

        // Given
        coEvery { productsRepository.getProductList() } returns flowOf(Result.success(products))

        // When
        productsViewModel.
        coVerify { productsRepository.getProductList() }

        // Then
        val uiState = productsViewModel.productsUIState.value
        assertEquals(products, uiState.products)
    }
}
