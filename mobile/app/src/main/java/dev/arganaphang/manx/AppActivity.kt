package dev.arganaphang.manx

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import dev.arganaphang.manx.routes.Router
import dev.arganaphang.manx.ui.theme.ManxTheme

@AndroidEntryPoint
class AppActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ManxTheme {
                Router()
            }
        }
    }
}