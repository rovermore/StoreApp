package com.example.storeapp.tests.domain

import com.example.storeapp.domain.cart.usecase.DiscountCalculator
import com.example.storeapp.mocks.CartDTOMock
import junit.framework.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.MockitoAnnotations

class DiscountCalculatorTest {

    private lateinit var discountCalculator: DiscountCalculator

    private val cart = CartDTOMock.cartDTO
    private val discount = 8.0

    @Before
    fun setupCommon() {
        MockitoAnnotations.initMocks(this)
        discountCalculator = DiscountCalculator()
    }

    @Test
    fun `when getCart success cartRepository getProducts is called`() {
        val result = discountCalculator.calculateDiscount(cart.items)
        Assert.assertEquals(discount, result)
    }
}