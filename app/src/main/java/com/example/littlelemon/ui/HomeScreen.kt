package com.example.littlelemon.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.littlelemon.AppDatabase
import com.example.littlelemon.Profile
import com.example.littlelemon.R
import java.util.Locale

@Composable
fun HomeScreen(navController: NavController) {

    val context = LocalContext.current

    val database by lazy {
        Room.databaseBuilder(context, AppDatabase::class.java, "database").build()
    }

    val databaseMenuItems by database.menuItemDao().getAll().observeAsState(emptyList())

    var searchPhrase by remember {
        mutableStateOf("")
    }

    var category by remember {
        mutableStateOf("")
    }

    Surface {
        Column {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.padding(top = 20.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo), contentDescription = "logo",
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(80.dp)
                        .padding(20.dp)
                )
                Image(
                    painter = painterResource(id = R.drawable.profile), contentDescription = "logo",
                    modifier = Modifier
                        .padding(10.dp)
                        .border(2.dp, Color.Gray, CircleShape)
                        .clickable { navController.navigate(Profile.route) }
                        .size(40.dp)
                        .clip(CircleShape)
                        .align(Alignment.CenterEnd),
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color(0xFF495E57))
                    .padding(20.dp)
            ) {
                Column {
                    Text(
                        text = stringResource(id = R.string.app_name),
                        color = Color(0xFFF4CE14),
                        fontSize = 40.sp
                    )
                    Row(modifier = Modifier.padding(bottom = 10.dp)) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth(0.65f)
                                .padding(top = 1.dp, end = 10.dp)
                        ) {
                            Text(
                                text = "Chicago",
                                color = Color(0xFFFFFFFF),
                                fontSize = 20.sp,
                                modifier = Modifier.padding(bottom = 13.dp)
                            )
                            Text(
                                text = stringResource(id = R.string.short_description),
                                color = Color(0xFFFFFFFF),
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Thin,
                                lineHeight = 14.sp
                            )
                        }
                        Image(
                            painter = painterResource(id = R.drawable.hero_image),
                            contentDescription = "hero",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .clip(RoundedCornerShape(16.dp))
                                .aspectRatio(1f)
                                .align(Alignment.CenterVertically)
                        )
                    }

                    TextField(
                        value = searchPhrase,
                        onValueChange = {
                            searchPhrase = it
                            category = ""
                        },
                        placeholder = {
                            Text(
                                text = "Enter search phrase",
                                fontSize = 13.sp,
                                modifier = Modifier.padding(0.dp)
                            )
                        },
                        colors = TextFieldDefaults.colors().copy(
                            focusedContainerColor = Color(0xFFFFFFFF),
                            unfocusedContainerColor = Color(0xFFFFFFFF),
                        ),
                        singleLine = true,
                        shape = RoundedCornerShape(8.dp),
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Outlined.Search,
                                contentDescription = null
                            )
                        },
                        textStyle = TextStyle.Default.copy(
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Thin
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                }
            }
            val categories = databaseMenuItems.map { it.category }.toSet()
            var menuFilteredItems =
                if (searchPhrase.isEmpty()) databaseMenuItems else databaseMenuItems.filter {
                    it.title.contains(searchPhrase, ignoreCase = true)
                }
            menuFilteredItems =
                if (category.isEmpty()) menuFilteredItems else menuFilteredItems.filter { it.category == category }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                categories.forEach {
                    Button(
                        onClick = {
                            category = it
                        },
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors()
                            .copy(
                                containerColor = Color(0xFFF4CE14), contentColor = Color(0xFF495E57)
                            ),
                        contentPadding = PaddingValues(5.dp),
                    ) {
                        Text(fontSize = 12.sp, text = it.uppercase(Locale.getDefault()))
                    }
                }
            }
            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp), color = Color(0xFF5E5E5E)
            )

            MenuItemList(menuFilteredItems)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    HomeScreen(rememberNavController())
}
