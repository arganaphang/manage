package dev.arganaphang.manage.ui.screen.main

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import dev.arganaphang.manage.component.BottomBar
import dev.arganaphang.manage.routing.MainNavGraph

@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun MainScreen(navController: NavHostController = rememberAnimatedNavController()) {
    Scaffold(
        bottomBar = {
            BottomBar(navController = navController)
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
        ) {
            MainNavGraph(navController = navController)
        }
    }
}

@Preview
@Composable
fun Preview() {
    MainScreen()
}