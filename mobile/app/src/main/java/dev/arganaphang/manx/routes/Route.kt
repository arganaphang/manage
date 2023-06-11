package dev.arganaphang.manx.routes

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import dev.arganaphang.manx.ui.screen.create.CreateScreen
import dev.arganaphang.manx.ui.screen.dashboard.DashboardScreen
import dev.arganaphang.manx.ui.screen.home.HomeScreen
import dev.arganaphang.manx.ui.screen.setting.SettingScreen
import dev.arganaphang.manx.ui.screen.splash.SplashScreen


sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Main : Screen("main") {
        object Home : Screen("home")
        object Dashboard : Screen("dashboard")
        object Setting : Screen("setting")
    }

    object Create : Screen("create")
}

@Composable
fun Router() {
    val navHostController = rememberNavController()
    NavHost(
        navController = navHostController,
        startDestination = Screen.Splash.route
    ) {
        composable(Screen.Splash.route) {
            SplashScreen(onNavigateToMain = {
                navHostController.navigate(Screen.Main.route) {
                    navHostController.popBackStack()
                    launchSingleTop = true
                }
            })
        }
        navigation(
            route = Screen.Main.route,
            startDestination = Screen.Main.Home.route
        ) {
            composable(Screen.Main.Home.route) {
                HomeScreen()
            }
            composable(Screen.Main.Dashboard.route) {
                DashboardScreen()
            }
            composable(Screen.Main.Setting.route) {
                SettingScreen()
            }
        }

        composable(Screen.Create.route) {
            CreateScreen()
        }

    }
}