package com.farimarwat.krossrivedemo

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.farimarwat.krossrive.core.KrossRiveAnimation
import com.farimarwat.krossrive.core.rememberKrossRiveAnimationState
import com.farimarwat.krossrive.model.KrossRiveConfig
import com.farimarwat.krossrive.model.KrossRiveContentFit
import com.farimarwat.krossrive.model.KrossRiveResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import krossrivedemo.composeapp.generated.resources.Res
import krossrivedemo.composeapp.generated.resources.compose_multiplatform

@Composable
@Preview
fun App() {
    MaterialTheme {
        var showContent by remember { mutableStateOf(false) }
        val riveBytes by produceState<ByteArray?>(initialValue = null) {
            value = Res.readBytes("files/login.riv")
        }
        val animationState = riveBytes?.let {
            rememberKrossRiveAnimationState(
                config = KrossRiveConfig(
                    //resource = KrossRiveResource.Url("https://cdn.rive.app/animations/off_road_car_v7.riv")
                    resource = KrossRiveResource.Bytes(it)
                )
            )
        }
        Column(
            modifier = Modifier
                .background(Color.White)
                .safeContentPadding()
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Button(onClick = { showContent = !showContent }) {
                Text("Click me!")
            }
            if (animationState != null) {
                KrossRiveAnimation(
                    state = animationState,
                    modifier = Modifier.
                    fillMaxWidth()
                        .height(200.dp)
                )
            } else {
                Text("Loading animation…")
            }
        }
    }
}