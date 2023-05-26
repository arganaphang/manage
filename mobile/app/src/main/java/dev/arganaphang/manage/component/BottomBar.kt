package dev.arganaphang.manage.component


import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import dev.arganaphang.manage.routing.Graph
import dev.arganaphang.manage.ui.theme.DarkGray

@Composable
fun BottomBar(navController: NavHostController) {
    val screens = listOf(
        BottomBarScreen.Home,
        BottomBarScreen.Dashboard,
        BottomBarScreen.Setting,
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val bottomBarDestination = screens.any { it.route == currentDestination?.route }
    if (bottomBarDestination) {
        NavigationBar(
            containerColor = if (isSystemInDarkTheme()) DarkGray else Color.White,
        ) {
            screens.forEach { screen ->
                AddItem(
                    screen = screen,
                    currentDestination = currentDestination,
                    navController = navController
                )
            }
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: BottomBarScreen,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    NavigationBarItem(
        label = {
            Text(text = screen.title)
        },
        icon = {
            Icon(
                imageVector = screen.icon,
                contentDescription = screen.title
            )
        },
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.route
        } == true,
        onClick = {
            when (screen.route) {
                currentDestination?.route -> {} // Do Nothing to prevent re-render in the same route
                else -> {
                    // Prevent Crash or miss navigate
                    val screenName = if (
                        screen.route == navController.graph.startDestinationRoute
                    ) {
                        Graph.MAIN
                    } else {
                        screen.route
                    }
                    navController.navigate(screenName) {
                        popUpTo(navController.graph.startDestinationId)
                        launchSingleTop = true
                    }
                }
            }
        }
    )
}


sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Home : BottomBarScreen(
        route = Graph.MAIN_HOME,
        title = "Home",
        icon = Icons.Filled.Home
    )

    object Dashboard : BottomBarScreen(
        route = Graph.MAIN_DASHBOARD,
        title = "Dashboard",
        icon = Icons.Default.Done
    )

    object Setting : BottomBarScreen(
        route = Graph.MAIN_SETTING,
        title = "Setting",
        icon = Icons.Outlined.Settings
    )
}