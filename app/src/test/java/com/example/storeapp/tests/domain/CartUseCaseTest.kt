package com.example.storeapp.tests.domain

import com.example.storeapp.domain.base.Error
import com.example.storeapp.domain.base.Failure
import com.example.storeapp.domain.base.Success
import com.example.storeapp.domain.base.get
import com.example.storeapp.domain.cart.model.CartDTO
import com.example.storeapp.domain.cart.model.ProductSelectionDTO
import com.example.storeapp.domain.cart.repository.CartRepository
import com.example.storeapp.domain.cart.usecase.CartUseCase
import com.example.storeapp.domain.cart.usecase.DiscountCalculator
import com.example.storeapp.mocks.CartDTOMock
import com.example.storeapp.mocks.ProductDTOMock
import junit.framework.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class CartUseCaseTest {

    @Mock
    lateinit var cartRepository: CartRepository

    @Mock
    lateinit var discountCalculator: DiscountCalculator

    private lateinit var cartUseCase: CartUseCase

    private val productSelection = ProductSelectionDTO(products = ProductDTOMock.productDTOList.toMutableList())
    private val successProducts = Success(productSelection)
    private val cart = CartDTOMock.cartDTO
    private val successCart = Success(cart)
    private val error = Failure(Error.UncompletedOperation(""))
    private val discount = 8.0

    @Before
    fun setupCommon() {
        MockitoAnnotations.initMocks(this)
        cartUseCase = CartUseCase(
            cartRepository,
            discountCalculator
        )
    }

    @Test
    fun `when getCart success cartRepository getProducts is called`() {
        Mockito.`when`(cartRepository.getProducts()).thenReturn(successProducts)
        Mockito.`when`(discountCalculator.calculateDiscount(cart.items)).thenReturn(discount)
        cartUseCase.getCart()
        Mockito.verify(cartRepository, Mockito.times(1)).getProducts()
    }
    @Test
    fun `when getCart success cartRepository getProducts returns success`() {
        Mockito.`when`(cartRepository.getProducts()).thenReturn(successProducts)
        Mockito.`when`(discountCalculator.calculateDiscount(cart.items)).thenReturn(discount)
        val result = cartUseCase.getCart()
        Assert.assertEquals(successCart.value.discount, (result.get() as CartDTO).discount)
        Assert.assertEquals(successCart.value.totalAmount, (result.get() as CartDTO).totalAmount)
        Assert.assertEquals(successCart.value.totalBeforeDiscount, (result.get() as CartDTO).totalBeforeDiscount)
    }

    @Test
    fun `when getCart failure cartRepository getProducts returns error`() {
        Mockito.`when`(cartRepository.getProducts()).thenReturn(error)
        Mockito.`when`(discountCalculator.calculateDiscount(cart.items)).thenReturn(discount)
        val result = cartUseCase.getCart()
        Assert.assertEquals(error, result)
    }

    @Test
    fun `when addProduct success cartRepository saveProducts is called`() {
        Mockito.`when`(cartRepository.getProducts()).thenReturn(successProducts)
        Mockito.`when`(cartRepository.saveProducts(productSelection)).thenReturn(Success(true))
        cartUseCase.addProduct(productSelection.products[0])
        Mockito.verify(cartRepository, Mockito.times(1))
            .saveProducts(productSelection)
    }
    @Test
    fun `when addProduct success cartRepository saveProducts returns success`() {
        Mockito.`when`(cartRepository.getProducts()).thenReturn(successProducts)
        Mockito.`when`(cartRepository.saveProducts(productSelection)).thenReturn(Success(true))
        val result = cartUseCase.addProduct(productSelection.products[0])
        Assert.assertEquals(Success(true), result)
    }

    @Test
    fun `when addProduct failure cartRepository saveProducts returns success`() {
        Mockito.`when`(cartRepository.getProducts()).thenReturn(error)
        Mockito.`when`(cartRepository.saveProducts(ProductSelectionDTO(products = mutableListOf(productSelection.products[0]))))
            .thenReturn(Success(true))
        val result = cartUseCase.addProduct(productSelection.products[0])
        Assert.assertEquals(Success(true), result)
    }

    @Test
    fun `when addProduct failure cartRepository saveProducts returns error`() {
        Mockito.`when`(cartRepository.getProducts()).thenReturn(error)
        Mockito.`when`(cartRepository.saveProducts(ProductSelectionDTO(products = mutableListOf(productSelection.products[0]))))
            .thenReturn(error)
        val result = cartUseCase.addProduct(productSelection.products[0])
        Assert.assertEquals(error, result)
    }

    @Test
    fun `when deleteProduct success cartRepository saveProducts is called`() {
        Mockito.`when`(cartRepository.getProducts()).thenReturn(successProducts)
        Mockito.`when`(cartRepository.saveProducts(productSelection)).thenReturn(Success(true))
        cartUseCase.deleteProduct(productSelection.products[0])
        Mockito.verify(cartRepository, Mockito.times(1))
            .saveProducts(productSelection)
    }
    @Test
    fun `when deleteProduct success cartRepository saveProducts returns success`() {
        Mockito.`when`(cartRepository.getProducts()).thenReturn(successProducts)
        Mockito.`when`(cartRepository.saveProducts(productSelection)).thenReturn(Success(true))
        val result = cartUseCase.deleteProduct(productSelection.products[0])
        Assert.assertEquals(Success(true), result)
    }

    @Test
    fun `when deleteProduct failure cartRepository saveProducts returns error`() {
        Mockito.`when`(cartRepository.getProducts()).thenReturn(error)
        Mockito.`when`(cartRepository.saveProducts(ProductSelectionDTO(products = mutableListOf(productSelection.products[0]))))
            .thenReturn(error)
        val result = cartUseCase.deleteProduct(productSelection.products[0])
        Assert.assertEquals(error, result)
    }

    @Test
    fun `when clearCart success cartRepository clearProducts is called`() {
        Mockito.`when`(cartRepository.clearProducts()).thenReturn(Success(true))
        cartUseCase.clearCart()
        Mockito.verify(cartRepository, Mockito.times(1))
            .clearProducts()
    }
    @Test
    fun `when clearCart success cartRepository clearProducts returns success`() {
        Mockito.`when`(cartRepository.clearProducts()).thenReturn(Success(true))
        val result = cartUseCase.clearCart()
        Assert.assertEquals(Success(true), result)
    }

    @Test
    fun `when clearCart failure cartRepository clearProducts returns error`() {
        Mockito.`when`(cartRepository.clearProducts()).thenReturn(error)
        val result = cartUseCase.clearCart()
        Assert.assertEquals(error, result)
    }

}