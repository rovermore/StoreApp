package com.example.storeapp.data.product.model

data class CatalogResponse(
    val products: List<Product?>
)

data class Product(
    val code: String?,
    val name: String?,
    val price: Double?
)