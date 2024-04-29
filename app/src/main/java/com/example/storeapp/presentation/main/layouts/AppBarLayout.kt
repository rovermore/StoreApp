package com.example.storeapp.presentation.main.layouts

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Magenta
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.storeapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
    onCartClicked: () -> Unit
) {
    TopAppBar(
        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Magenta),
        title = {
            Text(
                text = "Cabify Store",
                fontWeight = FontWeight.Bold,
                color = Color.White,
                fontSize = 18.sp
            )
        },
        actions = {
            IconButton(
                onClick = { onCartClicked() }
            ){
                Icon(painter = painterResource(id = R.drawable.baseline_shopping_cart_24), contentDescription = "shopping cart")
            }
        }
    )
}