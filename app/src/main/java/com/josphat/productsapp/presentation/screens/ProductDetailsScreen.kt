package com.josphat.productsapp.presentation.screens

@Composable
fun ProductDetailsScreen(productId: Int, viewModel: ProductsViewModel) {
    val product = viewModel.getProductById(productId).collectAsState(initial = null).value

    product?.let {
        //Todo: 2 Above handles nullability: If product is not null, display product details
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(text = product.title, fontWeight = FontWeight.Bold, fontSize = 20.sp)
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "Price: $${product.price}")
            Text(text = "Discount: ${product.discountPercentage}%")
            Text(text = "Rating: ${product.rating}")
            Text(text = "Stock: ${product.stock}")
            Spacer(modifier = Modifier.height(10.dp))

            // Image
            Image(
                painter = rememberAsyncImagePainter(product.thumbnail),
                contentDescription = product.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
            )

            Text(text = product.description)
        }
    } ?: run {
        // If product is null, show a loading indicator
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator() // Loading state
        }
    }
}
