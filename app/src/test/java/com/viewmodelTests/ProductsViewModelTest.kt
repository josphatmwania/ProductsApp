package com.viewmodelTests

import com.josphat.productsapp.data.ProductsRepository
import com.josphat.productsapp.presentation.viewmodel.ProductsViewModel
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before


class ProductsViewModelTest {
    private val productsRepository = mockk<ProductsRepository>(relaxed = true) // provide a mockinstance of ProductsRepository
    private lateinit var productsViewModel: ProductsViewModel
    @Before
    fun setUp() { //Todo: run before our tests run
        Dispatchers.setMain(Dispatchers.Unconfined)
        productsViewModel = ProductsViewModel(productsRepository)
    }
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}