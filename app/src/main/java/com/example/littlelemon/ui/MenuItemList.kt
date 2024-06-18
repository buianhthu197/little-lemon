package com.example.littlelemon.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.littlelemon.MenuItemRoom

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MenuItemList(items: List<MenuItemRoom>) {
    LazyColumn(
        modifier = Modifier.padding(start = 20.dp, end = 20.dp, bottom = 40.dp)
    ) {
        itemsIndexed(items) { index, menuItem ->
            Row(modifier = Modifier.padding(5.dp), verticalAlignment = Alignment.CenterVertically) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(0.7f)
                        .padding(end = 5.dp)
                ) {
                    Text(menuItem.title)
                    Text(
                        text = menuItem.description,
                        lineHeight = 14.sp,
                        fontWeight = FontWeight.Thin,
                        fontSize = 12.sp
                    )
                    Text(
                        text = '$' + menuItem.price.toString(),
                        fontWeight = FontWeight.Light,
                        fontSize = 14.sp
                    )
                }
                GlideImage(
                    model = menuItem.image, // URL of the image
                    contentDescription = "Menu image", // Description for accessibility
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.aspectRatio(1f)

                )
            }
            if (index < items.lastIndex)
                HorizontalDivider(color = Color(0xFF808080))
        }
    }
}
