package com.farimarwat.krossrivedemo

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.farimarwat.krossrive.core.KrossRiveAnimation
import com.farimarwat.krossrive.core.KrossRiveAnimationState
import com.farimarwat.krossrive.core.rememberKrossRiveAnimationState
import com.farimarwat.krossrive.model.KrossRiveConfig
import com.farimarwat.krossrive.model.KrossRiveContentFit
import com.farimarwat.krossrive.model.KrossRiveResource
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import krossrivedemo.composeapp.generated.resources.Res
import krossrivedemo.composeapp.generated.resources.compose_multiplatform

@Composable
@Preview
fun App() {
    MaterialTheme {
        val riveBytes by produceState<ByteArray?>(initialValue = null) {
            value = Res.readBytes("files/login.riv")
        }
        val animationState = riveBytes?.let {
            rememberKrossRiveAnimationState(
                config = KrossRiveConfig(
                    //resource = KrossRiveResource.Url("https://cdn.rive.app/animations/juice_v7.riv"),
                    resource = KrossRiveResource.Bytes(it),
                    stateMachine = null
                )
            )
        }
        LoginScreen(
            animationState = animationState
        )
    }
}

@Composable
fun LoginScreen(animationState: KrossRiveAnimationState?) {
    val scope = rememberCoroutineScope()
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val requiredEmail = "testing@gmail.com"
    val requiredPassword = "testing"
    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier
            .background(Color.White)
            .safeContentPadding()
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (animationState != null) {
            KrossRiveAnimation(
                state = animationState,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )
        } else {
            Text("Loading animation…")
        }

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = email,
            onValueChange = {
                email = it
                scope.launch {
                    animationState?.setNumber("Login Machine", "numLook", it.length.toFloat())
                }
            },
            label = { Text("Email") },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .onFocusChanged { focusState ->
                    if (focusState.isFocused) {
                        scope.launch {
                            animationState?.setBoolean("Login Machine", "isHandsUp", false)
                            delay(500)
                            animationState?.setBoolean("Login Machine", "isChecking", true)
                        }
                    }
                }
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .onFocusChanged { focusState ->
                    if (focusState.isFocused) {
                        scope.launch {
                            animationState?.setBoolean("Login Machine", "isHandsUp", true)
                        }
                    }
                }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                focusManager.clearFocus()
                animationState?.setBoolean("Login Machine", "isHandsUp", false)
                scope.launch {
                    delay(2000)
                    if (email == requiredEmail && password == requiredPassword) {
                        animationState?.fire("Login Machine", "trigSuccess")
                    } else {
                        animationState?.fire("Login Machine", "trigFail")
                    }
                }
            },
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            Text("Login")
        }
    }
}
