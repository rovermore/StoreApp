package com.example.storeapp.domain.cart.model

class CartDTO(
    val items: List<CartItemDTO>,
    val totalBeforeDiscount: Double,
    val discount: Double,
    val totalAmount: Double
)

data class CartItemDTO(
    val code: String,
    val name: String,
    val totalAmount: Double,
    val totalItem: Int,
)