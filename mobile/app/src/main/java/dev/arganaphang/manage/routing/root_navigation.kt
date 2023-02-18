package dev.arganaphang.manage.routing

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import dev.arganaphang.manage.ui.screen.main.MainScreen

@Composable
fun RootNavigationGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = Graph.MAIN
    ) {
        composable(route = Graph.MAIN) {
            MainScreen()
        }
    }
}

object Graph {
    const val ROOT = "root_graph"
    const val MAIN = "main_graph"
    const val MAIN_HOME = "main_home_graph"
    const val MAIN_DASHBOARD = "main_dashboard_graph"
    const val MAIN_SETTING = "main_setting_graph"
    const val DETAIL = "detail_graph"
    const val CREATE = "create_graph"
}
