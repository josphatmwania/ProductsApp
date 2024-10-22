//package com.josphat.productsapp.uiTests
//
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.test.*
//import androidx.compose.ui.test.junit4.createComposeRule
//import androidx.test.ext.junit.runners.AndroidJUnit4
//import com.josphat.productsapp.data.model.Product
//import com.josphat.productsapp.presentation.screens.ProductsScreen
//import com.josphat.productsapp.presentation.viewmodel.ProductsViewModel
//import io.mockk.coEvery
//import io.mockk.mockk
//import kotlinx.coroutines.flow.MutableStateFlow
//import kotlinx.coroutines.flow.StateFlow
//import org.junit.Before
//import org.junit.Rule
//import org.junit.Test
//import org.junit.runner.RunWith
//
//@RunWith(AndroidJUnit4::class)
//class ProductsScreenTest {
//
//    @get:Rule
//    val composeTestRule = createComposeRule()
//
//    private val mockViewModel = mockk<ProductsViewModel>(relaxed = true)
//
//    private lateinit var productsStateFlow: MutableStateFlow<List<Product>>
//
//    @Before
//    fun setup() {
//        //Todo: Use Sample products to mock the real product
//        val sampleProducts = listOf(
//            Product(
//                id = 1,
//                title = "Sample Product 1",
//                description = "This is a sample description",
//                images = listOf("image1.jpg", "image2.jpg"),
//                price = 29.99,
//                discountPercentage = 10.0,
//                rating = 4.5,
//                stock = 100,
//                brand = "Sample Brand",
//                category = "Sample Category",
//                thumbnail = "Not added"
//            ),
//            Product(
//                id = 2,
//                title = "Sample Product 2",
//                description = "This is another sample description",
//                images = listOf("image1.jpg", "image2.jpg"),
//                price = 49.99,
//                discountPercentage = 5.0,
//                rating = 4.0,
//                stock = 50,
//                brand = "Sample Brand 2",
//                category = "Sample Category 2",
//                thumbnail = "Not added"
//            )
//        )
//
//        // Create a MutableStateFlow for products
//        productsStateFlow = MutableStateFlow(sampleProducts)
//
//        // Mock the products StateFlow in the ViewModel
//        coEvery { mockViewModel.products } returns productsStateFlow
//    }
//
//    @Test
//    fun productsScreen_displaysProducts() {
//        // Set the content with the mocked ViewModel
//        composeTestRule.setContent {
//            ProductsScreen(viewModel = mockViewModel)
//        }
//
//        // Check that the screen contains the products' titles
//        composeTestRule.onNodeWithText("Sample Product 1").assertIsDisplayed()
//        composeTestRule.onNodeWithText("Sample Product 2").assertIsDisplayed()
//
//        // Check the product descriptions
//        composeTestRule.onNodeWithText("This is a sample description").assertIsDisplayed()
//        composeTestRule.onNodeWithText("This is another sample description").assertIsDisplayed()
//    }
//
//    @Test
//    fun productsScreen_displaysLoadingIndicatorWhenEmpty() {
//        // Mock an empty product list
//        productsStateFlow.value = emptyList()
//
//        composeTestRule.setContent {
//            ProductsScreen(viewModel = mockViewModel)
//        }
//
//        // Todo: to check that CircularProgressIndicator is displayed (you need to add the appropriate test tag in your composable)
//        composeTestRule.onNodeWithTag("loadingIndicator").assertIsDisplayed()
//    }
//}
