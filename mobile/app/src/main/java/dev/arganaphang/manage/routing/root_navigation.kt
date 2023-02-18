package dev.arganaphang.manage.routing

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.AnimatedNavHost
import dev.arganaphang.manage.ui.screen.main.MainScreen

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun RootNavigationGraph(navController: NavHostController) {
    AnimatedNavHost(
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
    const val ROOT = "graph_root"
    const val MAIN = "graph_main"
    const val MAIN_HOME = "main_home"
    const val MAIN_DASHBOARD = "graph_main_dashboard"
    const val MAIN_SETTING = "graph_main_setting"
    const val CREATE_ROOT = "graph_create_root"
    const val CREATE = "graph_create"
    const val DETAIL_ROOT = "graph_detail_root"
    const val DETAIL = "graph_detail"
}
