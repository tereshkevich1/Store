package com.example.store.feature.store.presentation.cart_screen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.store.feature.store.presentation.models.CartPack
import com.example.store.ui.theme.DpSpSize.paddingExtraLarge
import com.example.store.ui.theme.DpSpSize.paddingMedium
import com.example.store.ui.theme.DpSpSize.paddingSmall
import com.example.store.ui.theme.StoreTheme

@Composable
fun CartItem(
    totalPrice: String,
    cartPack: CartPack,
    modifier: Modifier = Modifier
) {
    ElevatedCard(
        modifier = modifier
            .clip(MaterialTheme.shapes.large)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingMedium)
        ) {
            Column(
                verticalArrangement = Arrangement.Center
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = cartPack.packWithDetailsUi.name,
                        style = MaterialTheme.typography.titleMedium
                    )

                    Spacer(modifier = Modifier.width(paddingSmall))

                    Text(
                        text = cartPack.quantity.formatted,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }

                Row(verticalAlignment = Alignment.CenterVertically) {

                    Text(
                        text = cartPack.packWithDetailsUi.discountedPrice.formatted,
                        style = MaterialTheme.typography.titleSmall
                    )

                    Spacer(modifier = Modifier.width(paddingSmall))

                    Text(
                        text = cartPack.packWithDetailsUi.originalPrice.formatted,
                        textDecoration = TextDecoration.LineThrough,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.outlineVariant
                    )
                }
            }

            Text(
                text = totalPrice,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Composable
@Preview
fun GoodItemPreview() {
    StoreTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = paddingMedium, vertical = paddingExtraLarge)
            ) {
                /*
                GoodItem(
                    packWithDetailsUi = book.toPackWithDetailsUi(),
                    onClick = {}
                )

                GoodItem(
                    packWithDetailsUi = book.toPackWithDetailsUi(),
                    onClick = {}
                )

                GoodItem(
                    packWithDetailsUi = sugar.toPackWithDetailsUi(),
                    onClick = {}
                )*/
            }
        }
    }
}
