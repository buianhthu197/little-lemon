package com.example.littlelemon

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.littlelemon.ui.HomeScreen
import com.example.littlelemon.ui.OnboardingScreen
import com.example.littlelemon.ui.ProfileScreen

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Onboarding.route
    ) {
        composable(Home.route) {
            HomeScreen(navController = navController)
        }
        composable(Onboarding.route) {
            OnboardingScreen(navController = navController)
        }
        composable(Profile.route) {
            ProfileScreen(navController = navController)
        }
    }
}