package dev.arganaphang.manx.ui.screen.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dev.arganaphang.manx.data.Transactions
import dev.arganaphang.manx.entity.TransactionEnum
import dev.arganaphang.manx.ui.component.TransactionCard
import dev.arganaphang.manx.ui.theme.ManxTheme
import java.util.Date

@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        LazyColumn {
            items(100) {
                TransactionCard(
                    Transactions(
                        1L,
                        "This is title",
                        120000L,
                        TransactionEnum.TransactionType.IN,
                        Date()
                    )
                )
            }
        }
    }
}

@Preview
@Composable
private fun PreviewScreen() {
    ManxTheme {
        Surface {
            HomeScreen()
        }
    }
}