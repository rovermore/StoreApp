package com.example.storeapp.presentation.main.layouts

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.storeapp.presentation.main.model.CartItemUIModel

@Composable
fun CartItem(cart: CartItemUIModel) {
    Row {
        Text(text = cart.name)
        Text(text = cart.totalItem)
        Text(text = cart.totalAmount)
    }
}

@Preview(widthDp = 340, showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun CartItemPreview() {
    CartItem(
        cart = CartItemUIModel(code = "VOUCHER", name = "Cabify Voucher", totalAmount = "5", totalItem = "5")
    )
}