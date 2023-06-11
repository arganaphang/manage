package dev.arganaphang.manx.ui.screen.splash

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dev.arganaphang.manx.ui.theme.ManxTheme
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(onNavigateToMain: () -> Unit) {
    LaunchedEffect(key1 = true) {
        delay(1000L)
        onNavigateToMain()
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text("Splash Screen")
    }
}

@Preview
@Composable
private fun PreviewScreen() {
    ManxTheme {
        Surface {
            SplashScreen(onNavigateToMain = {})
        }
    }
}