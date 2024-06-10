package com.example.storeapp.presentation.main.layouts

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.storeapp.R
import com.example.storeapp.presentation.main.model.CartItemUIModel
import com.example.storeapp.presentation.main.model.CartUIModel
import com.example.storeapp.presentation.main.model.ProductUIModel
import com.example.storeapp.presentation.widgets.Loader

@Composable
fun Cart(
    isCartLoading: Boolean,
    cart: CartUIModel,
    onAddClicked: (ProductUIModel) -> Unit,
    onRemoveClicked: (ProductUIModel) -> Unit,
    onBuyClicked: () -> Unit
) {

    LazyColumn(horizontalAlignment = Alignment.CenterHorizontally) {

        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                Divider(
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                        .width(32.dp)
                        .height(4.dp)
                        .align(Alignment.CenterHorizontally)
                        .clip(RoundedCornerShape(100))
                )
            }
        }

        if (isCartLoading) {
            item { Loader() }
        } else {

            items(cart.items) {
                CartItem(
                    item = it,
                    onAddClicked = { onAddClicked(it) },
                    onRemoveClicked = { onRemoveClicked(it) }
                )
            }
            item {
                SummaryCard(
                    keyText = stringResource(id = R.string.total_before_disocunt),
                    valueText = cart.totalBeforeDiscount.plus(" €")
                )
            }
            item {
                SummaryCard(
                    keyText = stringResource(id = R.string.discount),
                    valueText = cart.discount.plus(" €")
                )
            }
            item {
                SummaryCard(
                    keyText = stringResource(id = R.string.total),
                    valueText = cart.totalAmount.plus(" €")
                )
            }

            item {
                Button(
                    modifier = Modifier.fillMaxWidth().padding(8.dp),
                    onClick = { onBuyClicked() }
                ) {
                    Text(text = stringResource(id = R.string.buy))
                }
            }
        }
    }
}

@Composable
fun SummaryCard(keyText: String, valueText: String){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, end = 24.dp)) {
            Text(
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Start,
                text = keyText,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.End,
                text = valueText,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

@Preview(widthDp = 340, showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun CartPreview() {
    Cart(
        isCartLoading = false,
        cart = CartUIModel(
            items = listOf(
                CartItemUIModel(ProductUIModel("MUG", "Cofee Mug", "7.5"),  "25", "5"),
                CartItemUIModel(ProductUIModel("MUG", "Cofee Mug", "7.5"),  "25", "5"),
            ),
            totalBeforeDiscount = "230",
            discount = "30",
            totalAmount = "200",
        ),
        onAddClicked = {},
        onRemoveClicked = {},
        onBuyClicked = {}
    )
}