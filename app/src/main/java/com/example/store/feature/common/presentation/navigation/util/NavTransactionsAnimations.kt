package com.example.store.feature.common.presentation.navigation.util

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut

fun enterFadeTransition(): EnterTransition = fadeIn(
    animationSpec = tween(400)
)

fun exitFadeTransition(): ExitTransition = fadeOut(
    animationSpec = tween(400)
)

fun popEnterFadeTransition(): EnterTransition = fadeIn(
    animationSpec = tween(400)
)

fun popExitFadeTransition(): ExitTransition = fadeOut(
    animationSpec = tween(400)
)