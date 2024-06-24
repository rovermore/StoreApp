package com.example.storeapp.presentation.main.model

import androidx.compose.runtime.Stable

@Stable
data class CartUIModel(
    val items: List<CartItemUIModel> = emptyList(),
    val totalBeforeDiscount: String = "",
    val discount: String = "",
    val totalAmount: String = "",
    val hasItems: Boolean = items.isNotEmpty()
)

data class CartItemUIModel(
    val productUIModel: ProductUIModel,
    val totalAmount: String = "",
    val totalItem: String = "",
)