package com.example.storeapp.mocks

import com.example.storeapp.data.product.model.CatalogResponse
import com.example.storeapp.data.product.model.Product

object CatalogResponseMock {

    private val product1 = Product(
        code = "VOUCHER",
        name = "Cabify Voucher",
        price = 5.00
    )

    private val product2 = Product(
        code = "TSHIRT",
        name = "Cabify T-Shirt",
        price = 20.00
    )

    private val product3 = Product(
        code = "MUG",
        name = "Cabify Coffee Mug",
        price = 7.50
    )

    val catalogResponse = CatalogResponse(products = listOf(product1, product2, product3))
}