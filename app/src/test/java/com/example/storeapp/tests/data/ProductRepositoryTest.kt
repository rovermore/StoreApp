package com.example.storeapp.tests.data

import com.example.storeapp.data.base.APIError
import com.example.storeapp.data.base.APIErrorMapper
import com.example.storeapp.data.product.ProductRepositoryImpl
import com.example.storeapp.data.product.model.ProductResponseMapper
import com.example.storeapp.data.product.network.ProductNetworkDatasource
import com.example.storeapp.domain.base.Error
import com.example.storeapp.domain.base.Failure
import com.example.storeapp.domain.base.Success
import com.example.storeapp.domain.base.get
import com.example.storeapp.domain.product.repository.ProductRepository
import com.example.storeapp.mocks.CatalogResponseMock
import com.example.storeapp.mocks.ProductDTOMock
import junit.framework.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class ProductRepositoryTest {

    @Mock
    lateinit var productNetworkDatasource: ProductNetworkDatasource
    @Mock
    lateinit var apiErrorMapper: APIErrorMapper
    @Mock
    lateinit var productResponseMapper: ProductResponseMapper

    private lateinit var productRepository: ProductRepository

    private val successProducts = Success(CatalogResponseMock.catalogResponse)
    private val successProductsDTO = Success(ProductDTOMock.productDTOList)
    private val apiError = Failure(APIError.NotFound(""))
    private val error = Failure(Error.UncompletedOperation(""))


    @Before
    fun setupCommon() {
        MockitoAnnotations.initMocks(this)
        productRepository = ProductRepositoryImpl(
            productNetworkDatasource,
            apiErrorMapper,
            productResponseMapper
        )
    }

    @Test
    fun `when getProductsCatalog success productNetworkDatasource getProductsCatalog is called`() {
        Mockito.`when`(productNetworkDatasource.getProductsCatalog()).thenReturn(successProducts)
        productRepository.getProductCatalog()
        Mockito.verify(productNetworkDatasource, Mockito.times(1)).getProductsCatalog()
    }
    @Test
    fun `when getProductsCatalog success productNetworkDatasource getProductsCatalog returns success`() {
        Mockito.`when`(productNetworkDatasource.getProductsCatalog()).thenReturn(successProducts)
        Mockito.`when`(productResponseMapper.mapList(successProducts.value)).thenReturn(successProductsDTO.value)
        val result = productRepository.getProductCatalog()
        Assert.assertEquals(successProductsDTO, result)
    }

    @Test
    fun `when getProductsCatalog failure productNetworkDatasource getProductsCatalog returns error`() {
        Mockito.`when`(productNetworkDatasource.getProductsCatalog()).thenReturn(apiError)
        Mockito.`when`(apiErrorMapper.map(apiError.get())).thenReturn(error.get())
        val result = productRepository.getProductCatalog()
        Assert.assertEquals(error, result)
    }
}