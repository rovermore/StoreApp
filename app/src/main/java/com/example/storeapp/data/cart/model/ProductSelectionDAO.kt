package com.example.storeapp.data.cart.model

import com.example.storeapp.data.product.model.ProductDAO

class ProductSelectionDAO(
    val products: MutableList<ProductDAO> = mutableListOf()
)