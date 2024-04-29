package com.example.storeapp.presentation.main.layouts

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import com.example.storeapp.presentation.main.model.ProductUIModel

@Composable
fun ProductItem(
    product: ProductUIModel,
    onAddClicked: () -> Unit
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(text = product.name)
        Text(text = product.price)
        Button(
            onClick = { onAddClicked() },
        ) {
            Text("Add")
        }
    }
}

@Preview(widthDp = 340, showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun ProductItemPreview() {
    ProductItem(
        product = ProductUIModel(code = "VOUCHER", name = "Cabify Voucher", price = "5"),
        onAddClicked = {}
    )
}