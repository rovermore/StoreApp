package com.example.storeapp.domain.cart.model

import com.example.storeapp.domain.product.model.ProductDTO

data class CartDTO(
    val products: List<ProductDTO> = emptyList()
) {
}