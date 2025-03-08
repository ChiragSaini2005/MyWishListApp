package com.example.mywishlistapp

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp

@Composable
fun AppBarView(title: String, onBackNavClicked: () -> Unit = {}) {

    val navigationIcon: (@Composable () -> Unit)? =
        if (!title.contains("WishList")) {
            {
                IconButton(onClick = { onBackNavClicked() }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Default.ArrowBack,
                        tint = Color.White,
                        contentDescription = null
                    )
                }
            }
        } else {
            null
        }

    TopAppBar(
        title = {
            Text(
                text = title, color = colorResource(id = R.color.white),
                modifier = Modifier
                    .padding(start = 8.dp)
                    .heightIn(max = 64.dp)
            )
        },
        elevation = 6.dp,
        backgroundColor = colorResource(id = R.color.app_bar_color),
        modifier = Modifier.padding(top = 48.dp),
        navigationIcon = navigationIcon
    )
}

