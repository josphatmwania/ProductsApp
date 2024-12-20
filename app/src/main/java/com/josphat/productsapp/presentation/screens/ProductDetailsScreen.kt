package com.josphat.productsapp.presentation.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.josphat.productsapp.presentation.viewmodel.ProductsViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailsScreen(
    productId: Int,
    viewModel: ProductsViewModel,
    navController: NavController
) {
    // Collect the product by its ID using the correct ViewModel function
    val product = viewModel.getProductByID(productId).collectAsState(initial = null).value

    LaunchedEffect(productId) {
        product?.let {
            Log.d("PRODUCT_ID", it.description)
        }
//        Log.d("PRODUCT_ID", product.description ?: "Product is null")
//        Log.d("PRODUCT_ID", product.title ?: "Title is null")
    }

    product?.let { productItem ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // Todo: Add a TopAppBar with a Back Nav arrow(To Products Screen)
            
            TopAppBar(
                title = {Text(text = "Product Details")},
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                        
                    }
                },
                
            modifier = Modifier.padding(start = 16.dp),
            actions = {}
            )
            
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = productItem.title, fontWeight = FontWeight.Bold, fontSize = 20.sp)
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "Price: $${productItem.price}")
            Text(text = "Discount: ${productItem.discountPercentage}%")
            Text(text = "Rating: ${productItem.rating}")
            Text(text = "Stock: ${productItem.stock}")
            Spacer(modifier = Modifier.height(10.dp))

            // Image composable within the @Composable function
            Image(
                painter = rememberAsyncImagePainter(productItem.thumbnail),
                contentDescription = productItem.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
            )

            Text(text = productItem.description)
        }
    } ?: run {
        // Show loading state if the product is null
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
//            CircularProgressIndicator() // Loading state
        }
    }
}
