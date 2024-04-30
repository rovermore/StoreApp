package com.example.storeapp.mocks

import com.example.storeapp.domain.cart.model.CartDTO
import com.example.storeapp.domain.cart.model.CartItemDTO
import com.example.storeapp.domain.product.model.ProductDTO

object CartDTOMock {

    private val cartItemDTO1 = CartItemDTO(
        productDTO = ProductDTO(
            code = "VOUCHER",
            name = "Cabify Voucher",
            price = 5.00
        ),
        totalAmount = 15.0,
        totalItem = 3
    )
    private val cartItemDTO2 = CartItemDTO(
        productDTO = ProductDTO(
            code = "TSHIRT",
            name = "Cabify T-Shirt",
            price = 20.00
        ),
        totalAmount = 60.0,
        totalItem = 3
    )
    private val cartItemDTO3 = CartItemDTO(
        productDTO = ProductDTO(
            code = "MUG",
            name = "Cabify Coffee Mug",
            price = 7.50
        ),
        totalAmount = 15.0,
        totalItem = 2
    )

    val cartDTO = CartDTO(
        items = listOf(cartItemDTO1, cartItemDTO2, cartItemDTO3),
        totalBeforeDiscount = 90.0,
        discount = 8.0,
        totalAmount = 82.0
    )
}