package dev.arganaphang.manage.ui.screen.main.home

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import dev.arganaphang.manage.R
import dev.arganaphang.manage.routing.Graph


@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun HomeFragment(navController: NavHostController = rememberAnimatedNavController()) {

    navController.graph.hierarchy.forEach { a -> println(a.route) }
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                shape = RoundedCornerShape(12.dp),
                onClick = {
                    navController.navigate(Graph.CREATE)
                }
            ) {
                Icon(imageVector = Icons.Filled.Add, stringResource(R.string.add))
            }
        }
    ) { padding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text("Home")
            }
        }

    }
}

@Preview
@Composable
fun Preview() {
    HomeFragment()
}