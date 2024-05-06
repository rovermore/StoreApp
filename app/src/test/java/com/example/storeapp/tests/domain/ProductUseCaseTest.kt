package com.example.storeapp.tests.domain

import com.example.storeapp.domain.base.Error
import com.example.storeapp.domain.base.Failure
import com.example.storeapp.domain.base.Success
import com.example.storeapp.domain.product.repository.ProductRepository
import com.example.storeapp.domain.product.usecase.ProductUseCase
import com.example.storeapp.mocks.ProductDTOMock
import junit.framework.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class ProductDAOUseCaseTest {

    @Mock
    lateinit var productRepository: ProductRepository

    private lateinit var productUseCase: ProductUseCase

    private val successProducts = Success(ProductDTOMock.productDTOList)
    private val error = Failure(Error.UncompletedOperation(""))

    @Before
    fun setupCommon() {
        MockitoAnnotations.initMocks(this)
        productUseCase = ProductUseCase(
            productRepository
        )
    }

    @Test
    fun `when getProductsCatalog success productRepository getProductsCatalog is called`() {
        productUseCase.getProductsCatalog()
        Mockito.verify(productRepository, Mockito.times(1)).getProductCatalog()
    }
    @Test
    fun `when getProductsCatalog success productRepository getProductsCatalog returns success`() {
        Mockito.`when`(productRepository.getProductCatalog()).thenReturn(successProducts)
        val result = productUseCase.getProductsCatalog()
        Assert.assertEquals(successProducts, result)
    }

    @Test
    fun `when getProductsCatalog failure productRepository getProductsCatalog returns error`() {
        Mockito.`when`(productRepository.getProductCatalog()).thenReturn(error)
        val result = productUseCase.getProductsCatalog()
        Assert.assertEquals(error, result)
    }
}