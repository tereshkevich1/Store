package com.example.store.feature.auth.presentation.sign_in

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.example.store.R
import com.example.store.feature.auth.presentation.components.AuthTextField
import com.example.store.feature.auth.presentation.sign_in.SignInScreenEvent.ShowToast
import com.example.store.ui.theme.DpSpSize.paddingMedium
import com.example.store.ui.theme.DpSpSize.paddingSmall
import kotlinx.coroutines.launch

@Composable
fun SignInRoute(
    onNavigateToPackList: () -> Unit,
    onNavigateToSignUp: () -> Unit,
    viewModel: SignInViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current

    val lifecycleOwner = LocalLifecycleOwner.current
    LaunchedEffect(Unit) {
        lifecycleOwner.lifecycleScope.launch {
            viewModel.events
                .flowWithLifecycle(lifecycleOwner.lifecycle, Lifecycle.State.STARTED)
                .collect { event ->
                    when (event) {
                        SignInScreenEvent.NavigateToPackList -> onNavigateToPackList()

                        is ShowToast -> {
                            Toast.makeText(
                                context,
                                event.message.asString(context),
                                Toast.LENGTH_LONG
                            ).show()
                        }

                        SignInScreenEvent.NavigateToSignUp -> onNavigateToSignUp()
                    }
                }
        }
    }

    SignInScreen(
        state = state,
        onAction = viewModel::onAction,
        modifier = modifier
    )
}

@Composable
fun SignInScreen(
    state: SignInState,
    onAction: (SignInScreenAction) -> Unit,
    modifier: Modifier
) {
    Column(
        horizontalAlignment = CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxSize()
            .padding(all = paddingMedium)
    ) {
        Text(
            text = stringResource(id = R.string.sign_in),
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(modifier = Modifier.height(paddingMedium))

        AuthTextField(
            text = state.emailText,
            onValueChange = {
                onAction(SignInScreenAction.OnEnterEmail(it))
            },
            error = state.emailError?.asString() ?: "",
            keyboardType = KeyboardType.Email,
            hint = stringResource(id = R.string.email)
        )

        Spacer(modifier = Modifier.height(paddingMedium))

        AuthTextField(
            text = state.passwordText,
            onValueChange = {
                onAction(SignInScreenAction.OnEnterPassword(it))
            },
            hint = stringResource(id = R.string.password_hint),
            keyboardType = KeyboardType.Password,
            error = state.passwordError?.asString() ?: "",
            isPasswordVisible = state.isPasswordVisible,
            onPasswordToggleClick = {
                onAction(SignInScreenAction.OnTogglePasswordVisibility)
            }
        )

        Spacer(modifier = Modifier.height(paddingMedium))

        Button(
            onClick = {
                onAction(SignInScreenAction.OnSignIn)
            },
            enabled = !state.isLoading,
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp)
        ) {
            if (state.isLoading) {
                CircularProgressIndicator()
            } else {
                Text(
                    text = stringResource(id = R.string.sign_in)
                )
            }
        }

        Spacer(modifier = Modifier.height(paddingSmall))

        Text(
            text = buildAnnotatedString {
                append(stringResource(id = R.string.dont_have_an_account_yet))
                append(" ")
                val signUpText = stringResource(id = R.string.register)
                withStyle(
                    style = SpanStyle(
                        color = MaterialTheme.colorScheme.primary
                    )
                ) {
                    append(signUpText)
                }
            },
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier
                .clickable {
                    onAction(SignInScreenAction.OnSignUp)
                }
        )
    }
}
