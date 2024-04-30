package com.example.storeapp.domain.cart.model

import com.example.storeapp.domain.product.model.ProductDTO

class CartDTO(
    val items: List<CartItemDTO>,
    val totalBeforeDiscount: Double,
    val discount: Double,
    val totalAmount: Double
)

data class CartItemDTO(
    val productDTO: ProductDTO,
    val totalAmount: Double,
    val totalItem: Int,
)