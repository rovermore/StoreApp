package com.example.storeapp.presentation.main.layouts

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.storeapp.presentation.base.ErrorUI
import com.example.storeapp.presentation.main.MainViewModel
import com.example.storeapp.presentation.main.model.CartItemUIModel
import com.example.storeapp.presentation.main.model.CartUIModel
import com.example.storeapp.presentation.main.model.ProductUIModel
import com.example.storeapp.presentation.widgets.ErrorView
import com.example.storeapp.presentation.widgets.Loader
import com.example.storeapp.presentation.widgets.StoreAppBar
import kotlinx.coroutines.launch


@Composable
fun MainActivityLayout(
    viewModel: MainViewModel = viewModel()
) {

    val productsCatalog by viewModel.productsCatalog.collectAsStateWithLifecycle()
    val cart by viewModel.cart.collectAsStateWithLifecycle()
    val isLoading by viewModel.isLoading.collectAsStateWithLifecycle()
    val isCartLoading by viewModel.isCartLoading.collectAsStateWithLifecycle()
    val error by viewModel.error.collectAsStateWithLifecycle()

    Content(
        productsCatalog = productsCatalog,
        cart = cart,
        isLoading = isLoading,
        isCartLoading = isCartLoading,
        error = error,
        onCartClicked = { viewModel.getCart() },
        onAddProduct = { viewModel.addProduct(it) },
        onRemoveProduct = { viewModel.deleteProduct(it) },
        onBuyClicked = { viewModel.clearCart() }
    )

}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Content(
    productsCatalog: List<ProductUIModel>,
    cart: CartUIModel,
    isLoading: Boolean,
    isCartLoading: Boolean,
    error: ErrorUI,
    onCartClicked: () -> Unit,
    onAddProduct: (ProductUIModel) -> Unit,
    onRemoveProduct: (ProductUIModel) -> Unit,
    onBuyClicked: () -> Unit,
) {

    val coroutineScope = rememberCoroutineScope()

    val sheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        skipHalfExpanded = true
    )

    Column {

        StoreAppBar(
            onCartClicked = {
                coroutineScope.launch {
                    onCartClicked()
                    sheetState.show()
                }
            },
            cartHasItems = cart.hasItems
        )

        ModalBottomSheetLayout(
            sheetState = sheetState,
            sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 26.dp),
            sheetContent = {

                Cart(
                    isCartLoading = isCartLoading,
                    cart = cart,
                    onAddClicked = { onAddProduct(it) },
                    onRemoveClicked = { onRemoveProduct(it) },
                    onBuyClicked = {
                        coroutineScope.launch {
                            sheetState.hide()
                            onBuyClicked()
                        }
                    }
                )
            }
        ) {
            LazyColumn(
                modifier = Modifier.padding(top = 4.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (error is ErrorUI.None) {
                    items(productsCatalog) {
                        ProductItem(
                            product = it,
                            onAddClicked = { onAddProduct(it) }
                        )
                    }
                } else {
                    item {
                        ErrorView(modifier = Modifier.fillParentMaxWidth()) {

                        }
                    }
                }

                if (isLoading)
                    item { Loader() }
            }
        }
    }
}


@Preview(widthDp = 340, showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun MainActivityPreview() {
    Content(
        productsCatalog = listOf(
            ProductUIModel("VOUCHER", "Cabify Voucher", "5"),
            ProductUIModel("VOUCHER", "Cabify Voucher", "5"),
            ProductUIModel("MUG", "Cabify Cofee Mug", "7.5"),
        ),
        cart = CartUIModel(
            items = listOf(
                CartItemUIModel(ProductUIModel("VOUCHER", "Cabify Voucher", "5"), "25", "5"),
                CartItemUIModel(ProductUIModel("VOUCHER", "Cabify Voucher", "5"), "25", "5"),
            ),
            discount = "30",
            totalAmount = "200",
        ),
        isLoading = false,
        isCartLoading = false,
        error = ErrorUI.None,
        onCartClicked = { },
        onAddProduct = {},
        onRemoveProduct = {},
        onBuyClicked = {}
    )
}

@Preview(widthDp = 340, showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun MainActivityWithLoaderPreview() {
    Content(
        productsCatalog = listOf(),
        cart = CartUIModel(
            items = listOf(
                CartItemUIModel(ProductUIModel("MUG", "Cabify Cofee Mug", "7.5"), "25", "5"),
                CartItemUIModel(ProductUIModel("MUG", "Cabify Cofee Mug", "7.5"), "25", "5"),
            ),
            discount = "30",
            totalAmount = "200",
        ),
        isLoading = true,
        isCartLoading = false,
        error = ErrorUI.None,
        onCartClicked = { },
        onAddProduct = {},
        onRemoveProduct = {},
        onBuyClicked = {}
    )
}

@Preview(widthDp = 340, showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun MainActivityWithErrorPreview() {
    Content(
        productsCatalog = listOf(
            ProductUIModel("VOUCHER", "Cabify Voucher", "5"),
            ProductUIModel("TSHIRT", "Cabify T-Shirt", "20"),
            ProductUIModel("MUG", "Cabify Cofee Mug", "7.5"),
        ),
        cart = CartUIModel(
            items = listOf(
                CartItemUIModel(ProductUIModel("TSHIRT", "Cabify T-Shirt", "20"), "25", "5"),
                CartItemUIModel(ProductUIModel("MUG", "Cabify Cofee Mug", "7.5"), "25", "5"),
            ),
            discount = "30",
            totalAmount = "200",
        ),
        isLoading = false,
        isCartLoading = false,
        error = ErrorUI.GenericError(""),
        onCartClicked = { },
        onAddProduct = {},
        onRemoveProduct = {},
        onBuyClicked = {}
    )
}
