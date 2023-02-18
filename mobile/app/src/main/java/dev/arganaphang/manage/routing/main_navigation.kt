package dev.arganaphang.manage.routing

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import dev.arganaphang.manage.ui.screen.main.dashboard.DashboardFragment
import dev.arganaphang.manage.ui.screen.main.home.HomeFragment
import dev.arganaphang.manage.ui.screen.main.setting.SettingFragment

@Composable
fun MainNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = Graph.MAIN,
        startDestination = Graph.MAIN_HOME
    ) {
        composable(route = Graph.MAIN_HOME) {
            HomeFragment()
        }
        composable(route = Graph.MAIN_DASHBOARD) {
            DashboardFragment()
        }
        composable(route = Graph.MAIN_SETTING) {
            SettingFragment()
        }
    }
}