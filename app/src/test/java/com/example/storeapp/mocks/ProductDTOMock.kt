package com.example.storeapp.mocks

import com.example.storeapp.domain.product.model.ProductDTO

object ProductDTOMock {

    private val productDTO11 = ProductDTO(
        code = "VOUCHER",
        name = "Cabify Voucher",
        price = 5.00
    )
    private val productDTO12 = ProductDTO(
        code = "VOUCHER",
        name = "Cabify Voucher",
        price = 5.00
    )
    private val productDTO13 = ProductDTO(
        code = "VOUCHER",
        name = "Cabify Voucher",
        price = 5.00
    )
    private val productDTO21 = ProductDTO(
        code = "TSHIRT",
        name = "Cabify T-Shirt",
        price = 20.00
    )
    private val productDTO22 = ProductDTO(
        code = "TSHIRT",
        name = "Cabify T-Shirt",
        price = 20.00
    )
    private val productDTO23 = ProductDTO(
        code = "TSHIRT",
        name = "Cabify T-Shirt",
        price = 20.00
    )
    private val productDTO31 = ProductDTO(
        code = "MUG",
        name = "Cabify Coffee Mug",
        price = 7.50
    )
    private val productDTO32 = ProductDTO(
        code = "MUG",
        name = "Cabify Coffee Mug",
        price = 7.50
    )

    val productDTOList = listOf(
        productDTO11,
        productDTO12,
        productDTO13,
        productDTO21,
        productDTO22,
        productDTO23,
        productDTO31,
        productDTO32
    )
}