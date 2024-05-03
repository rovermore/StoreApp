package com.example.storeapp.tests.data

import com.example.storeapp.data.cart.CartRepositoryImpl
import com.example.storeapp.data.cart.local.ProductLocalDatasource
import com.example.storeapp.data.cart.model.ProductDTOMapper
import com.example.storeapp.data.product.model.ProductDAOMapper
import com.example.storeapp.domain.base.Error
import com.example.storeapp.domain.base.Failure
import com.example.storeapp.domain.base.Success
import com.example.storeapp.domain.cart.model.ProductSelectionDTO
import com.example.storeapp.domain.cart.repository.CartRepository
import com.example.storeapp.mocks.ProductDAOMock
import com.example.storeapp.mocks.ProductDTOMock
import junit.framework.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class CartRepositoryTest {

    @Mock
    lateinit var productLocalDatasource: ProductLocalDatasource

    @Mock
    lateinit var productDTOMapper: ProductDTOMapper

    @Mock
    lateinit var productDAOMapper: ProductDAOMapper

    private lateinit var cartRepository: CartRepository

    private val productSelection = ProductSelectionDTO(ProductDTOMock.productDTOList.toMutableList())
    private val successProductsDTO = Success(productSelection)
    private val error = Failure(Error.UncompletedOperation(""))
    private val productSelectionDAO = ProductDAOMock.productSelectionDAO


    @Before
    fun setupCommon() {
        MockitoAnnotations.initMocks(this)
        cartRepository = CartRepositoryImpl(
            productLocalDatasource,
            productDTOMapper,
            productDAOMapper
        )
    }

    @Test
    fun `when saveProducts success productLocalDatasource saveProducts is called`() {
        Mockito.`when`(productLocalDatasource.saveProducts(productSelectionDAO)).thenReturn(Success(true))
        Mockito.`when`(productDTOMapper.map(productSelection)).thenReturn(productSelectionDAO)
        cartRepository.saveProducts(productSelection)
        Mockito.verify(productLocalDatasource, Mockito.times(1)).saveProducts(productSelectionDAO)
    }
    @Test
    fun `when saveProducts success productLocalDatasource saveProducts returns success`() {
        Mockito.`when`(productLocalDatasource.saveProducts(productSelectionDAO)).thenReturn(Success(true))
        Mockito.`when`(productDTOMapper.map(productSelection)).thenReturn(productSelectionDAO)
        val result = cartRepository.saveProducts(productSelection)
        Assert.assertEquals(Success(true), result)
    }

    @Test
    fun `when saveProducts failure productLocalDatasource saveProducts returns error`() {
        Mockito.`when`(productLocalDatasource.saveProducts(productSelectionDAO)).thenReturn(error)
        Mockito.`when`(productDTOMapper.map(productSelection)).thenReturn(productSelectionDAO)
        val result = cartRepository.saveProducts(productSelection)
        Assert.assertEquals(error, result)
    }

    @Test
    fun `when getProducts success productLocalDatasource retrieveProducts is called`() {
        Mockito.`when`(productLocalDatasource.retrieveProducts()).thenReturn(Success(productSelectionDAO))
        Mockito.`when`(productDAOMapper.mapSelection(productSelectionDAO)).thenReturn(productSelection)
        cartRepository.getProducts()
        Mockito.verify(productLocalDatasource, Mockito.times(1)).retrieveProducts()
    }
    @Test
    fun `when getProducts success productLocalDatasource retrieveProducts returns success`() {
        Mockito.`when`(productLocalDatasource.retrieveProducts()).thenReturn(Success(productSelectionDAO))
        Mockito.`when`(productDAOMapper.mapSelection(productSelectionDAO)).thenReturn(productSelection)
        val result = cartRepository.getProducts()
        Assert.assertEquals(successProductsDTO, result)
    }

    @Test
    fun `when getProducts failure productLocalDatasource retrieveProducts returns error`() {
        Mockito.`when`(productLocalDatasource.retrieveProducts()).thenReturn(error)
        val result = cartRepository.getProducts()
        Assert.assertEquals(error, result)
    }

    @Test
    fun `when clearProducts success productLocalDatasource retrieveProducts is called`() {
        Mockito.`when`(productLocalDatasource.deleteProducts()).thenReturn(Success(true))
        cartRepository.clearProducts()
        Mockito.verify(productLocalDatasource, Mockito.times(1)).deleteProducts()
    }
    @Test
    fun `when clearProducts success productLocalDatasource retrieveProducts returns success`() {
        Mockito.`when`(productLocalDatasource.deleteProducts()).thenReturn(Success(true))
        val result = cartRepository.clearProducts()
        Assert.assertEquals(Success(true), result)
    }

    @Test
    fun `when clearProducts failure productLocalDatasource retrieveProducts returns error`() {
        Mockito.`when`(productLocalDatasource.deleteProducts()).thenReturn(error)
        val result = cartRepository.clearProducts()
        Assert.assertEquals(error, result)
    }
}