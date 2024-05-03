package com.example.storeapp.data.product.model

import com.google.gson.annotations.SerializedName

data class CatalogResponseDAO(
    @SerializedName("products")
    val productDAO: List<ProductDAO?>
)

data class ProductDAO(
    val code: String?,
    val name: String?,
    val price: Double?
)