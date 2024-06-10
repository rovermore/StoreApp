package com.example.storeapp.mocks

import com.example.storeapp.presentation.main.model.CartItemUIModel
import com.example.storeapp.presentation.main.model.CartUIModel
import com.example.storeapp.presentation.main.model.ProductUIModel

object CartUIModelMock {
    private val cartItem1 = CartItemUIModel(
        productUIModel = ProductUIModel(
            code = "VOUCHER",
            name = "Voucher",
            price = "5"
        ),
        totalAmount = "15",
        totalItem = "3"
    )
    private val cartItem2 = CartItemUIModel(
        productUIModel = ProductUIModel(
            code = "TSHIRT",
            name = "T-Shirt",
            price = "20"
        ),
        totalAmount = "60",
        totalItem = "3"
    )
    private val cartItem3 = CartItemUIModel(
        productUIModel = ProductUIModel(
            code = "MUG",
            name = "Coffee Mug",
            price = "7.5"
        ),
        totalAmount = "15",
        totalItem = "2"
    )

    val cart = CartUIModel(
        items = listOf(cartItem1, cartItem2, cartItem3),
        totalBeforeDiscount = "90",
        discount = "8",
        totalAmount = "82"
    )

}