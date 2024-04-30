package com.example.storeapp.presentation.main.model

data class CartUIModel(
    val items: List<CartItemUIModel> = emptyList(),
    val totalBeforeDiscount: String = "",
    val discount: String = "",
    val totalAmount: String = ""
)

data class CartItemUIModel(
    val productUIModel: ProductUIModel,
    val totalAmount: String = "",
    val totalItem: String = "",
)