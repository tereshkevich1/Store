package com.example.store.feature.store.presentation.packs_list_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.example.store.feature.store.presentation.packs_list_screen.bottom_sheet.CartBottomSheet
import com.example.store.feature.store.presentation.packs_list_screen.components.PackItem
import com.example.store.ui.theme.DpSpSize.paddingMedium
import com.example.store.ui.theme.DpSpSize.paddingSmall
import kotlinx.coroutines.launch

@Composable
fun PacksListRoute(
    viewModel: PacksListViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    val lifecycleOwner = LocalLifecycleOwner.current
    LaunchedEffect(Unit) {
        lifecycleOwner.lifecycleScope.launch {
            viewModel.events
                .flowWithLifecycle(lifecycleOwner.lifecycle, Lifecycle.State.STARTED)
                .collect {
                    when (it) {
                        is PackListEvent.ShowToast -> {

                        }
                    }
                }
        }
    }

    PacksListScreen(
        state = state,
        onAction = viewModel::onAction,
        modifier = modifier
    )
}

@Composable
fun PacksListScreen(
    state: PackListState,
    onAction: (PackListAction) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    LazyColumn(
        modifier = modifier
            .fillMaxSize(),
        contentPadding = PaddingValues(all = paddingMedium),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(paddingSmall)
    ) {
        items(
            items = state.packList,
            key = { pack -> pack.id }
        )
        { pack ->
            PackItem(
                packWithDetailsUi = pack,
                onClick = { onAction(PackListAction.OnPackClick(pack)) },
                context = context
            )
        }
    }

    if (state.showBottomSheet) {
        state.selectedPack?.let { pack ->
            CartBottomSheet(
                pack = pack,
                quantity = state.quantity,
                totalPrice = state.totalPrice,
                errorMessage = state.quantityError?.asString() ?: "",
                onQuantityChanged = { onAction(PackListAction.OnQuantityChanged(it)) },
                onCancelClick = { onAction(PackListAction.OnBottomSheetClose) },
                onSaveClick = { onAction(PackListAction.OnAddPackToCart(pack, state.quantity)) },
                onDismissRequest = { onAction(PackListAction.OnBottomSheetClose) }
            )
        }
    }
}