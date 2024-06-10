package com.example.storeapp.mocks

import com.example.storeapp.presentation.main.model.ProductUIModel

object ProductUIModelMock {

    private val product11 = ProductUIModel(
        code = "VOUCHER",
        name = "Voucher",
        price = "5"
    )
    private val product12 = ProductUIModel(
        code = "VOUCHER",
        name = "Voucher",
        price = "5"
    )
    private val product13 = ProductUIModel(
        code = "VOUCHER",
        name = "Voucher",
        price = "5"
    )
    private val product21 = ProductUIModel(
        code = "TSHIRT",
        name = "T-Shirt",
        price = "20"
    )
    private val product22 = ProductUIModel(
        code = "TSHIRT",
        name = "T-Shirt",
        price = "20"
    )
    private val product23 = ProductUIModel(
        code = "TSHIRT",
        name = "T-Shirt",
        price = "20"
    )
    private val product31 = ProductUIModel(
        code = "MUG",
        name = "Coffee Mug",
        price = "7.5"
    )
    private val product32 = ProductUIModel(
        code = "MUG",
        name = "Coffee Mug",
        price = "7.5"
    )

    val productUIModelList = listOf(
        product11,
        product12,
        product13,
        product21,
        product22,
        product23,
        product31,
        product32
    )
}