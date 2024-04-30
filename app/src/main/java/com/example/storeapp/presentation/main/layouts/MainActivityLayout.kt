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
import com.example.storeapp.presentation.widgets.AppBar
import com.example.storeapp.presentation.widgets.ErrorView
import com.example.storeapp.presentation.widgets.Loader
import kotlinx.coroutines.launch


@Composable
fun MainActivityLayout(
    viewModel: MainViewModel = viewModel()
) {

    val productsCatalog by viewModel.productsCatalog.collectAsStateWithLifecycle()
    val cart by viewModel.cart.collectAsStateWithLifecycle()
    val isLoading by viewModel.isLoading.collectAsStateWithLifecycle()
    val error by viewModel.error.collectAsStateWithLifecycle()

    Content(
        productsCatalog = productsCatalog,
        cart = cart,
        isLoading = isLoading,
        error = error,
        onCartClicked = { viewModel.getCart() },
        onAddProduct = { viewModel.addProduct(it) },
        addCartItem = {},
        removeCartItem = {}
    )

}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Content(
    productsCatalog: List<ProductUIModel>,
    cart: CartUIModel,
    isLoading: Boolean,
    error: ErrorUI,
    onCartClicked: () -> Unit,
    onAddProduct: (ProductUIModel) -> Unit,
    addCartItem: (String) -> Unit,
    removeCartItem: (String) -> Unit,
) {

    val coroutineScope = rememberCoroutineScope()

    val sheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        skipHalfExpanded = true
    )

    Column {

        AppBar(
            onCartClicked = {
                coroutineScope.launch {
                    onCartClicked()
                    sheetState.show()
                }
            }
        )

        ModalBottomSheetLayout(
            sheetState = sheetState,
            sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 26.dp),
            sheetContent = {

                Cart(
                    cart = cart,
                    onAddClicked = { addCartItem(it) },
                    onRemoveClicked = { removeCartItem(it) }
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
            ProductUIModel("TSHIRT", "Cabify T-Shirt", "20"),
            ProductUIModel("MUG", "Cabify Cofee Mug", "7.5"),
        ),
        cart = CartUIModel(
            items = listOf(
                CartItemUIModel("VOUCHER", "Cabify Voucher", "25", "5"),
                CartItemUIModel("VOUCHER", "Cabify Voucher", "25", "5"),
            ),
            discount = "30",
            totalAmount = "200",
        ),
        isLoading = false,
        error = ErrorUI.None,
        onCartClicked = { },
        onAddProduct = {},
        addCartItem = {},
        removeCartItem = {}
    )
}

@Preview(widthDp = 340, showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun MainActivityWithLoaderPreview() {
    Content(
        productsCatalog = listOf(),
        cart = CartUIModel(
            items = listOf(
                CartItemUIModel("VOUCHER", "Cabify Voucher", "25", "5"),
                CartItemUIModel("VOUCHER", "Cabify Voucher", "25", "5"),
            ),
            discount = "30",
            totalAmount = "200",
        ),
        isLoading = true,
        error = ErrorUI.None,
        onCartClicked = { },
        onAddProduct = {},
        addCartItem = {},
        removeCartItem = {}
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
                CartItemUIModel("VOUCHER", "Cabify Voucher", "25", "5"),
                CartItemUIModel("VOUCHER", "Cabify Voucher", "25", "5"),
            ),
            discount = "30",
            totalAmount = "200",
        ),
        isLoading = false,
        error = ErrorUI.GenericError(""),
        onCartClicked = { },
        onAddProduct = {},
        addCartItem = {},
        removeCartItem = {}
    )
}
