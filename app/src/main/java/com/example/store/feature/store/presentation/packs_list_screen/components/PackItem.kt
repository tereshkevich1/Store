package com.example.store.feature.store.presentation.packs_list_screen.components

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextDecoration
import com.example.store.feature.store.presentation.models.PackWithDetailsUi
import com.example.store.feature.store.presentation.models.toPriceDescription
import com.example.store.ui.theme.DpSpSize.paddingMedium
import com.example.store.ui.theme.DpSpSize.paddingSmall

@Composable
fun PackItem(
    packWithDetailsUi: PackWithDetailsUi,
    onClick: () -> Unit,
    context: Context,
    modifier: Modifier = Modifier
) {
    ElevatedCard(
        modifier = modifier
            .clip(MaterialTheme.shapes.large)
            .clickable { onClick() }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingMedium),
            verticalArrangement = Arrangement.Center
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                val discountedPrice = packWithDetailsUi.discountedPrice
                val originalPrice = packWithDetailsUi.originalPrice

                Text(
                    text = discountedPrice.formatted,
                    style = MaterialTheme.typography.titleMedium
                )

                if (discountedPrice.value != originalPrice.value) {
                    Spacer(modifier = Modifier.width(paddingSmall))

                    Text(
                        text = originalPrice.formatted,
                        textDecoration = TextDecoration.LineThrough,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.outlineVariant
                    )
                }

                Spacer(modifier = Modifier.width(paddingSmall))

                Text(
                    text = packWithDetailsUi.type.toPriceDescription(context),
                    style = MaterialTheme.typography.titleMedium
                )
            }

            Spacer(modifier = Modifier.width(paddingSmall))
            Text(
                text = packWithDetailsUi.name,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}
