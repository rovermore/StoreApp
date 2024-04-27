package com.example.storeapp.presentation.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.storeapp.presentation.main.model.ProductUIModel


@Composable
fun MainActivityLayout(
    viewModel: MainViewModel = viewModel()
) {

    val productsCatalog by viewModel.productsCatalog.collectAsStateWithLifecycle()

}

@Composable
fun Content(
    productsCatalog: List<ProductUIModel>
) {

}

@Preview(widthDp = 340, showBackground = true , backgroundColor = 0xFFFFFF)
@Composable
private fun MainActivitytPreview() {
    Content(
        productsCatalog = listOf(
            ProductUIModel("VOUCHER", "Cabify Voucher", "5"),
            ProductUIModel("TSHIRT", "Cabify T-Shirt", "20"),
            ProductUIModel("MUG", "Cabify Cofee Mug", "7.5"),)
    )
}
