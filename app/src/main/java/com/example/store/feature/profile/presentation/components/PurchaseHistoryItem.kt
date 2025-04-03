package com.example.store.feature.profile.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.example.store.ui.theme.DpSpSize

@Composable
fun PurchaseHistoryItem(
    onClick: () -> Unit,
    totalPrice: String,
    dataTime: String,
    modifier: Modifier = Modifier
) {
    ElevatedCard(
        modifier = modifier
            .clip(MaterialTheme.shapes.large)
            .clickable { onClick() }
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(DpSpSize.paddingMedium)
        ) {
            Text(
                text = dataTime,
                style = MaterialTheme.typography.bodyMedium
            )

            Text(
                text = totalPrice,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}