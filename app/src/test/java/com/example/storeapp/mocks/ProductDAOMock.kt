package com.example.storeapp.mocks

import com.example.storeapp.data.cart.model.ProductSelectionDAO
import com.example.storeapp.data.product.model.CatalogResponseDAO
import com.example.storeapp.data.product.model.ProductDAO

object ProductDAOMock {

    private val productDAO1 = ProductDAO(
        code = "VOUCHER",
        name = "Cabify Voucher",
        price = 5.00
    )

    private val productDAO2 = ProductDAO(
        code = "TSHIRT",
        name = "Cabify T-Shirt",
        price = 20.00
    )

    private val productDAO3 = ProductDAO(
        code = "MUG",
        name = "Cabify Coffee Mug",
        price = 7.50
    )

    val catalogResponseDAO = CatalogResponseDAO(productDAO = listOf(productDAO1, productDAO2, productDAO3))
    val productSelectionDAO = ProductSelectionDAO(products = mutableListOf(productDAO1, productDAO2, productDAO3))
}