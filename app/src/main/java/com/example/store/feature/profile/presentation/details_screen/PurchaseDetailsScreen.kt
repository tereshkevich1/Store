package com.example.store.feature.profile.presentation.details_screen

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.example.store.feature.profile.presentation.components.PurchaseDetailsItem
import com.example.store.ui.theme.DpSpSize.paddingMedium
import com.example.store.ui.theme.DpSpSize.paddingSmall
import kotlinx.coroutines.launch

@Composable
fun PurchaseDetailsRoute(
    purchaseId: String,
    viewModel: DetailsViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current

    val lifecycleOwner = LocalLifecycleOwner.current
    LaunchedEffect(Unit) {
        viewModel.loadDetails(purchaseId)

        lifecycleOwner.lifecycleScope.launch {
            viewModel.events
                .flowWithLifecycle(lifecycleOwner.lifecycle, Lifecycle.State.STARTED)
                .collect {
                    when (it) {
                        is DetailsScreeEvent.ShowToast -> Toast.makeText(
                            context,
                            it.message.asString(context),
                            Toast.LENGTH_LONG
                        )
                    }

                }
        }
    }

    PurchaseDetailsScreen(
        state = state,
        onAction = viewModel::onAction,
        modifier = Modifier
    )
}

@Composable
fun PurchaseDetailsScreen(
    state: DetailsScreenState,
    onAction: (DetailsScreeAction) -> Unit,
    modifier: Modifier
) {
    if (state.isLoading) {
        Box(
            modifier = modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        LazyColumn(
            modifier = modifier
                .fillMaxSize(),
            contentPadding = PaddingValues(all = paddingMedium),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(paddingSmall)
        ) {

            items(
                items = state.purchaseList,
                key = { purchase ->
                    purchase.packId
                }
            )
            { purchase ->
                PurchaseDetailsItem(
                    name = purchase.name,
                    price = purchase.price.formatted,
                    quantity = purchase.quantity
                )
            }
        }
    }
}

