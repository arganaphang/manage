package dev.arganaphang.manx.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.arganaphang.manx.data.Transactions
import dev.arganaphang.manx.entity.TransactionEnum
import dev.arganaphang.manx.ui.theme.Typography
import java.util.Date

@Composable
fun TransactionCard(trx: Transactions) {

    val type = when (trx.type) {
        TransactionEnum.TransactionType.IN -> "-"
        else -> "+"
    }
    val iconSize = 32.dp
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        // TODO: Change this placeholder
        Box(
            modifier = Modifier
                .width(iconSize)
                .height(iconSize)
                .clip(RoundedCornerShape(iconSize / 4))
                .background(Color.LightGray)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                trx.title,
                style = Typography.titleMedium,
                modifier = Modifier.fillMaxWidth(),
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
            Text(
                "${trx.createdAt}",
                style = Typography.labelSmall,
                modifier = Modifier.fillMaxWidth(),
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
        Text("$type Rp. ${trx.amount}", style = Typography.titleSmall)
    }
}


@Preview(
    showBackground = true
)
@Composable
fun TransactionCardPreview() {
    TransactionCard(
        trx = Transactions(
            1L,
            "This is title",
            120000L,
            TransactionEnum.TransactionType.IN,
            Date()
        )
    )
}