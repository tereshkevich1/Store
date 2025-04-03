@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.store.feature.store.presentation.packs_list_screen.bottom_sheet

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import com.example.store.R
import com.example.store.feature.store.presentation.models.PackWithDetailsUi
import com.example.store.feature.store.presentation.models.toPriceDescription
import com.example.store.ui.theme.DpSpSize.paddingMedium
import com.example.store.ui.theme.DpSpSize.paddingSmall

@Composable
fun CartBottomSheet(
    pack: PackWithDetailsUi,
    quantity: String,
    totalPrice: String,
    errorMessage: String,
    onQuantityChanged: (value: String) -> Unit,
    onDismissRequest: () -> Unit,
    onSaveClick: () -> Unit,
    onCancelClick: () -> Unit,
) {
    val context = LocalContext.current

    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        sheetState = rememberModalBottomSheetState()
    ) {
        Column(
            modifier = Modifier.padding(
                top = paddingSmall,
                bottom = paddingMedium,
                start = paddingMedium,
                end = paddingMedium
            )
        ) {
            Text(
                text = pack.name,
                style = MaterialTheme.typography.headlineSmall,
            )

            Spacer(modifier = Modifier.height(paddingSmall))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = pack.discountedPrice.formatted,
                    style = MaterialTheme.typography.titleMedium
                )

                Spacer(modifier = Modifier.width(paddingSmall))

                Text(
                    text = pack.originalPrice.formatted,
                    textDecoration = TextDecoration.LineThrough,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.outlineVariant
                )

                Spacer(modifier = Modifier.width(paddingSmall))

                Text(
                    text = pack.type.toPriceDescription(context),
                    style = MaterialTheme.typography.titleMedium
                )
            }

            Spacer(modifier = Modifier.height(paddingSmall))

            OutlinedTextField(
                value = quantity,
                onValueChange = { onQuantityChanged(it) },
                label = { Text(pack.unit) },
                supportingText = {
                    if (errorMessage.isNotBlank()) {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = errorMessage,
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                },
                isError = errorMessage.isNotBlank(),
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(paddingSmall))

            Text(
                text = stringResource(R.string.for_payment, totalPrice),
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = paddingSmall)
            )
        }

        ActionButtons(
            onCancelClick = onCancelClick,
            onSaveClick = onSaveClick,
            enabled = errorMessage.isBlank()
        )
    }
}