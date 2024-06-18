package com.example.littlelemon.ui

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.littlelemon.Onboarding
import com.example.littlelemon.R

@Composable
fun ProfileScreen(navController: NavController) {
    val SHARED_PREFS = "shared_prefs"
    val EMAIL_KEY = "email_key"
    val FIRST_NAME_KEY = "first_name_key"
    val LAST_NAME_KEY = "last_name_key"
    val context = LocalContext.current
    val sharedPreferences =
        context.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE)

    val email = sharedPreferences.getString(EMAIL_KEY, "").toString()
    val firstName = sharedPreferences.getString(FIRST_NAME_KEY, "").toString()
    val lastName = sharedPreferences.getString(LAST_NAME_KEY, "").toString()
    Column {
        Image(
            painter = painterResource(id = R.drawable.logo), contentDescription = "logo",
            modifier = Modifier
                .fillMaxWidth()
                .size(100.dp)
                .padding(20.dp)
                .align(Alignment.CenterHorizontally)
        )

        Text(
            text = stringResource(id = R.string.personal_info),
            fontSize = 17.sp,
            modifier = Modifier.padding(start = 10.dp, top = 30.dp, bottom = 30.dp)
        )
        TextField(
            modifier = Modifier
                .padding(start = 10.dp, top = 30.dp, end = 10.dp)
                .fillMaxWidth(),
            value = firstName,
            onValueChange = { },
            enabled = false,
            label = { Text("First Name") },
            singleLine = true
        )
        TextField(
            modifier = Modifier
                .padding(start = 10.dp, top = 30.dp, end = 10.dp)
                .fillMaxWidth(),
            value = lastName,
            onValueChange = { },
            enabled = false,
            label = { Text("Last Name") },
            singleLine = true
        )
        TextField(
            modifier = Modifier
                .padding(start = 10.dp, top = 30.dp, end = 10.dp)
                .fillMaxWidth(),
            value = email,
            onValueChange = { },
            enabled = false,
            label = { Text("Email") },
            singleLine = true
        )

        Button(
            onClick = {
                clearUserData(context)
                navController.navigate(Onboarding.route) },
            modifier = Modifier
                .padding(start = 10.dp, top = 50.dp, end = 10.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(10),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF4CE14))
        ) {
            Text(
                text = stringResource(id = R.string.log_out),
                fontSize = 18.sp,
                fontWeight = FontWeight.Thin,
                color = Color(0xFF333333)
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun ProfilePreview() {
    ProfileScreen(rememberNavController())
}
 fun clearUserData(context: Context) {
     val SHARED_PREFS = "shared_prefs"
     context.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE).edit().clear().apply()
 }