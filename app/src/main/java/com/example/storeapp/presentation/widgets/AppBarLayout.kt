package com.example.storeapp.presentation.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.storeapp.R
import com.example.storeapp.presentation.theme.Purple40

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun StoreAppBar(
    onCartClicked: () -> Unit,
    cartHasItems: Boolean
) {
    TopAppBar(
        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Purple40),
        title = {
            Text(
                text = "Store",
                fontWeight = FontWeight.Bold,
                color = Color.White,
                fontSize = 18.sp
            )
        },
        actions = {
            IconButton(
                modifier = Modifier
                    .semantics { testTagsAsResourceId = true }
                    .testTag("cartButton"),
                onClick = { onCartClicked() }
            ){
                Box(
                    modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.CenterVertically),
                    contentAlignment = Alignment.Center,) {

                    Icon(
                        painter = painterResource(id = R.drawable.baseline_shopping_cart_24),
                        contentDescription = "shopping cart",
                        tint = Color.White
                    )

                    if (cartHasItems)
                        Box(
                            modifier = Modifier
                                .background(
                                    color = Color.Red,
                                    shape = CircleShape
                                )
                                .padding(4.dp)
                                .align(Alignment.TopEnd),
                        )
                }

            }
        }
    )
}

@Preview(widthDp = 340, showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun AppBarPreview() {
    StoreAppBar(
        onCartClicked =  {},
        cartHasItems = true
    )
}