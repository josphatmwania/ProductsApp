package com.josphat.productsapp.presentation.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.josphat.productsapp.data.model.Product
import kotlinx.coroutines.flow.collectLatest
import com.josphat.productsapp.presentation.viewmodel.ProductsViewModel
import androidx.navigation.NavController
import com.josphat.productsapp.navigation.Screens // <-- Ensure this import is present

@Composable
fun ProductsScreen(viewModel: ProductsViewModel, navController: NavController) {
    val productList = viewModel.products.collectAsState().value
    val context = LocalContext.current

    // Log when the data list is updated
    Log.d("ProductsScreen", "Product list updated: ${productList.size} items")

    LaunchedEffect(key1 = viewModel.showErrorToastChannel) {
        viewModel.showErrorToastChannel.collectLatest { show ->
            if (show) {
                Log.e("ProductsScreen", "Error occurred while fetching products")
                Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
            }
        }
    }

    if (productList.isEmpty()) {
        Log.d("ProductsScreen", "Product list is empty, showing loader")
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            contentPadding = PaddingValues(16.dp)
        ) {
            items(productList) { product ->
                Log.d("ProductsScreen", "Displaying product: ${product.title}")
                ProductItem(
                    product = product,
                    onClick = {
                        navController.navigate(Screens.ProductDetailsScreen.route + "/${product.id}")
                    }
                )
            }
        }
    }
}

@Composable
fun ProductItem(product: Product, onClick: () -> Unit) {
    val imageState = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(product.thumbnail)
            .size(Size.ORIGINAL)
            .build()
    ).state

    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .height(300.dp)
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primaryContainer)
            .clickable { onClick() } // Add click listener to navigate to details
    ) {
        if (imageState is AsyncImagePainter.State.Error) {
            Log.e("ProductItem", "Failed to load image for product: ${product.title}")
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentAlignment = Alignment.Center
            ) {
//                CircularProgressIndicator()
            }
        }

        if (imageState is AsyncImagePainter.State.Success) {
            Log.d("ProductItem", "Successfully loaded image for product: ${product.title}")
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                painter = imageState.painter,
                contentDescription = product.title,
                contentScale = ContentScale.Crop
            )
        }

        Spacer(modifier = Modifier.height(6.dp))

        Column(
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            Text(
                text = product.title,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = "Price: $${product.price}",
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                modifier = Modifier.padding(horizontal = 16.dp),
                text = product.description,
                fontSize = 9.sp,
            )
        }
    }
}
