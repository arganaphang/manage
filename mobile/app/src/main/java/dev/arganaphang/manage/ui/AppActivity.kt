package dev.arganaphang.manage.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
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

@Composable
private fun Content() {
    ManageTheme {
        RootNavigationGraph(navController = rememberNavController())
    }
}

@Preview
@Composable
fun Preview() {
    Content()
}