package com.example.storeapp.presentation.main.layouts

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.storeapp.presentation.main.model.CartItemUIModel
import com.example.storeapp.presentation.main.model.ProductUIModel
import com.example.storeapp.presentation.theme.Purple40
import com.example.storeapp.presentation.theme.Purple80

@Composable
fun CartItem(
    item: CartItemUIModel,
    onAddClicked: (ProductUIModel) -> Unit,
    onRemoveClicked: (ProductUIModel) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
        ) {
            Text(
                modifier = Modifier.weight(1F),
                text = item.productUIModel.name,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold
            )
            Button(
                modifier = Modifier.weight(1F),
                onClick = { onRemoveClicked(item.productUIModel) },
                colors = ButtonDefaults.buttonColors(containerColor = Purple80)
            ) {
                Text(text = "-", color = Purple40, fontWeight = FontWeight.Bold)
            }

            Button(
                modifier = Modifier.weight(1F),
                onClick = { onAddClicked(item.productUIModel) },
                colors = ButtonDefaults.buttonColors(containerColor = Purple80)
            ) {
                Text(text = "+", color = Purple40, fontWeight = FontWeight.Bold)
            }

            Text(
                modifier = Modifier.weight(1F),
                textAlign = TextAlign.Center,
                text = item.totalItem,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold
            )

            Text(
                modifier = Modifier.weight(1F),
                textAlign = TextAlign.Center,
                text = item.totalAmount.plus(" €"),
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )



        }
    }
}

@Preview(widthDp = 340, showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun CartItemPreview() {
    CartItem(
        item = CartItemUIModel(ProductUIModel("MUG", "Cofee Mug", "7.5"), totalAmount = "5", totalItem = "5"),
        onAddClicked = {},
        onRemoveClicked = {}
    )
}