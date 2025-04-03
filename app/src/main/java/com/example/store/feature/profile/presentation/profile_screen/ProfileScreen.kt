package com.example.store.feature.profile.presentation.profile_screen

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.example.store.feature.profile.presentation.components.PurchaseSummaryItem
import com.example.store.ui.theme.DpSpSize.paddingExtraLarge
import com.example.store.ui.theme.DpSpSize.paddingLarge
import com.example.store.ui.theme.DpSpSize.paddingMedium
import com.example.store.ui.theme.DpSpSize.paddingSmall
import com.example.store.ui.theme.StoreTheme
import kotlinx.coroutines.launch

@Composable
fun ProfileScreenRoute(
    onDetailsScreenNavigate: (String) -> Unit,
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current

    val lifecycleOwner = LocalLifecycleOwner.current
    LaunchedEffect(Unit) {
        lifecycleOwner.lifecycleScope.launch {
            viewModel.events
                .flowWithLifecycle(lifecycleOwner.lifecycle, Lifecycle.State.STARTED)
                .collect {
                    when (it) {
                       is ProfileScreeEvent.NavigateToDetailsScreen -> onDetailsScreenNavigate(it.purchaseId)
                        is ProfileScreeEvent.ShowToast -> {
                            Toast.makeText(context, it.message.asString(context), Toast.LENGTH_LONG)
                        }
                    }
                }
        }
    }
    ProfileScreen(
        state = state,
        onAction = viewModel::onAction,
        modifier = Modifier
    )
}

@Composable
fun ProfileScreen(
    state: ProfileScreenState,
    onAction: (ProfileScreenAction) -> Unit,
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
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(top = paddingMedium),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = state.username,
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(modifier = Modifier.height(paddingLarge))

            LazyColumn(
                modifier = modifier
                    .weight(1f)
                    .fillMaxWidth(),
                contentPadding = PaddingValues(all = paddingMedium),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(paddingSmall)
            ) {

                items(
                    items = state.purchaseList
                )
                { purchase ->
                    PurchaseSummaryItem(
                        onClick = { onAction(ProfileScreenAction.OnPurchaseHistoryItemClick(purchase.documentId)) },
                        totalPrice = purchase.totalPrice.formatted,
                        dataTime = purchase.timestamp
                    )
                }
            }
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
                PurchaseSummaryItem({}, "2000 BYN", "02.04.2025 - 14:51")
                PurchaseSummaryItem({}, "2000 BYN", "02.04.2025 - 14:51")
                PurchaseSummaryItem({}, "2000 BYN", "02.04.2025 - 14:51")
            }
        }
    }
}