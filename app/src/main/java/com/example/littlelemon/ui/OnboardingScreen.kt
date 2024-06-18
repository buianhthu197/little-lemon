package com.example.littlelemon.ui

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.example.littlelemon.Home
import com.example.littlelemon.R

@Composable
fun OnboardingScreen(navController: NavController) {
    val context = LocalContext.current

    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    val SHARED_PREFS = "shared_prefs"
    val EMAIL_KEY = "email_key"
    val sharedPreferences = context.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE)

    val cachedEmail = sharedPreferences.getString(EMAIL_KEY, "").toString()

    if (!cachedEmail.isBlank()) navController.navigate(Home.route)

    Column {
        Image(
            painter = painterResource(id = R.drawable.logo), contentDescription = "logo",
            modifier = Modifier
                .fillMaxWidth()
                .size(100.dp)
                .padding(20.dp)
                .align(Alignment.CenterHorizontally)
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color(0xFF495E57))
                .size(100.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(id = R.string.onboarding_screen),
                fontSize = 20.sp,
                fontWeight = FontWeight.Thin,
                color = Color.White,
            )
        }
        Text(
            text = stringResource(id = R.string.personal_info),
            fontSize = 17.sp,
            modifier = Modifier.padding(start = 10.dp, top = 30.dp, bottom = 30.dp)
        )
        OutlinedTextField(
            modifier = Modifier
                .padding(start = 10.dp, top = 30.dp, end = 10.dp)
                .fillMaxWidth(),
            value = firstName,
            onValueChange = { firstName = it },
            label = { Text("First Name") }
        )
        OutlinedTextField(
            modifier = Modifier
                .padding(start = 10.dp, top = 30.dp, end = 10.dp)
                .fillMaxWidth(),
            value = lastName,
            onValueChange = { lastName = it },
            label = { Text("Last Name") }
        )
        OutlinedTextField(
            modifier = Modifier
                .padding(start = 10.dp, top = 30.dp, end = 10.dp)
                .fillMaxWidth(),
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") }
        )

        Button(
            onClick = {
                handleRegistration(
                    context,
                    firstName,
                    lastName,
                    email,
                    navController
                )
            },
            modifier = Modifier
                .padding(start = 10.dp, top = 50.dp, end = 10.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(10),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF4CE14))
        ) {
            Text(
                text = stringResource(id = R.string.register),
                fontSize = 18.sp,
                fontWeight = FontWeight.Thin,
                color = Color(0xFF333333)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OnboardingReview() {
    OnboardingScreen(rememberNavController())
}

fun handleRegistration(
    context: Context,
    firstName: String,
    lastName: String,
    email: String,
    navController: NavController
) {
    val SHARED_PREFS = "shared_prefs"
    val EMAIL_KEY = "email_key"
    val FIRST_NAME_KEY = "first_name_key"
    val LAST_NAME_KEY = "last_name_key"

    val inputValidated = !firstName.isBlank() && !lastName.isBlank() && !email.isBlank()
    if (inputValidated) {
        val sharedPreferences = context.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        editor.putString(EMAIL_KEY, email)
        editor.putString(FIRST_NAME_KEY, firstName)
        editor.putString(LAST_NAME_KEY, lastName)
        editor.apply()

        Toast.makeText(context, "Registration successful!", Toast.LENGTH_LONG).show()
        navController.navigate(Home.route)
    } else {
        Toast.makeText(
            context,
            "Registration unsuccessful. Please enter all data.",
            Toast.LENGTH_LONG
        ).show()
    }
}