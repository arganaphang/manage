package dev.arganaphang.manage.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import dev.arganaphang.manage.routing.RootNavigationGraph
import dev.arganaphang.manage.ui.theme.ManageTheme

class AppActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Content()
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun Content() {
    ManageTheme {
        RootNavigationGraph(navController = rememberAnimatedNavController())
    }
}

@Preview
@Composable
fun Preview() {
    Content()
}