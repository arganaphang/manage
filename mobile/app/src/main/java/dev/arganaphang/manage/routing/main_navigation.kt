@file:OptIn(ExperimentalAnimationApi::class)

package dev.arganaphang.manage.routing

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.navigation
import dev.arganaphang.manage.ui.screen.create.CreateScreen
import dev.arganaphang.manage.ui.screen.detail.DetailScreen
import dev.arganaphang.manage.ui.screen.main.dashboard.DashboardFragment
import dev.arganaphang.manage.ui.screen.main.home.HomeFragment
import dev.arganaphang.manage.ui.screen.main.setting.SettingFragment

@Composable
fun MainNavGraph(navController: NavHostController) {
    AnimatedNavHost(
        navController = navController,
        route = Graph.MAIN,
        startDestination = Graph.MAIN_HOME
    ) {
        composable(route = Graph.MAIN_HOME) {
            HomeFragment(navController)
        }
        composable(route = Graph.MAIN_DASHBOARD) {
            DashboardFragment(navController)
        }
        composable(route = Graph.MAIN_SETTING) {
            SettingFragment(navController)
        }
        createNavGraph(navController = navController)
        detailNavGraph(navController = navController)
    }
}

fun NavGraphBuilder.createNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.CREATE_ROOT,
        startDestination = Graph.CREATE
    ) {
        composable(route = Graph.CREATE) {
            CreateScreen(navController)
        }
    }
}

fun NavGraphBuilder.detailNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.DETAIL_ROOT,
        startDestination = Graph.DETAIL
    ) {
        composable(route = Graph.DETAIL) {
            DetailScreen(navController)
        }
    }
}