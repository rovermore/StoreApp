package com.example.storeapp.tests.presentation

import com.example.storeapp.domain.base.Success
import com.example.storeapp.domain.cart.usecase.CartUseCase
import com.example.storeapp.domain.product.model.ProductDTO
import com.example.storeapp.domain.product.usecase.ProductUseCase
import com.example.storeapp.mocks.CartDTOMock
import com.example.storeapp.mocks.CartUIModelMock
import com.example.storeapp.mocks.ProductDTOMock
import com.example.storeapp.mocks.ProductUIModelMock
import com.example.storeapp.presentation.base.ErrorUIMapper
import com.example.storeapp.presentation.main.MainViewModel
import com.example.storeapp.presentation.main.model.CartUIModelMapper
import com.example.storeapp.presentation.main.model.ProductUIModelMapper
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class MainViewModelTest {

    private lateinit var productUseCase: ProductUseCase

    private lateinit var errorUIMapper: ErrorUIMapper

    private lateinit var productUIModelMapper: ProductUIModelMapper

    private lateinit var cartUseCase: CartUseCase

    private lateinit var cartUIModelMapper: CartUIModelMapper

    private lateinit var viewModel: MainViewModel

    private val productListDTO = ProductDTOMock.productDTOList
    private val cartDTO = CartDTOMock.cartDTO
    private val successCart = Success(cartDTO)
    private val successList = Success(productListDTO)
    private val productUIModelList = ProductUIModelMock.productUIModelList
    private val cartUIModel = CartUIModelMock.cart


    @Before
    fun setup() = runTest {
        MockitoAnnotations.openMocks(this)
        productUseCase = Mockito.mock(ProductUseCase::class.java)
        errorUIMapper = Mockito.mock(ErrorUIMapper::class.java)
        productUIModelMapper = Mockito.mock(ProductUIModelMapper::class.java)
        cartUseCase = Mockito.mock(CartUseCase::class.java)
        cartUIModelMapper = Mockito.mock(CartUIModelMapper::class.java)
    }


    @Test
    fun `when viewModel init then productUseCase getProductsCatalog is called`() {
        Mockito.`when`(productUseCase.getProductsCatalog()).thenReturn(successList)
        Mockito.`when`(cartUseCase.getCart()).thenReturn(successCart)
        Mockito.`when`(cartUIModelMapper.map(cartDTO)).thenReturn(cartUIModel)
        runTest {
            viewModel = MainViewModel(
                productUseCase, errorUIMapper, productUIModelMapper, cartUseCase, cartUIModelMapper
            )
        }

        Mockito.verify(productUseCase).getProductsCatalog()
    }

    @Test
    fun `when viewModel init then cartUseCase getCart is called`() {
        Mockito.`when`(productUseCase.getProductsCatalog()).thenReturn(successList)
        Mockito.`when`(cartUseCase.getCart()).thenReturn(successCart)
        Mockito.`when`(cartUIModelMapper.map(cartDTO)).thenReturn(cartUIModel)
        runTest {
            viewModel = MainViewModel(
                productUseCase, errorUIMapper, productUIModelMapper, cartUseCase, cartUIModelMapper
            )
        }

        Mockito.verify(cartUseCase).getCart()
    }

    @Test
    fun `when addProduct is called cartUseCase addProduct is called`() = runTest {
        Mockito.`when`(productUseCase.getProductsCatalog()).thenReturn(successList)
        Mockito.`when`(cartUseCase.getCart()).thenReturn(successCart)
        Mockito.`when`(cartUseCase.addProduct(ProductDTO(
            productListDTO[0].code,
            productListDTO[0].name,
            productListDTO[0].price,
        ))).thenReturn(Success(true))
        Mockito.`when`(cartUIModelMapper.map(cartDTO)).thenReturn(cartUIModel)
        viewModel = MainViewModel(
            productUseCase, errorUIMapper, productUIModelMapper, cartUseCase, cartUIModelMapper
        )
        viewModel.addProduct(productUIModelList[0])
        Mockito.verify(cartUseCase).getCart()

    }

}