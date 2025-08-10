package com.farimarwat.krossrive.core

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import app.rive.runtime.kotlin.RiveAnimationView
import com.farimarwat.krossrive.model.KrossRiveConfig
import com.farimarwat.krossrive.model.KrossRiveResource

@Composable
actual fun rememberKrossRiveAnimationState(
    config: KrossRiveConfig
): KrossRiveAnimationState {
    val context = LocalContext.current
    return remember(config) {
        val view = RiveAnimationView(context)
        val state = AndroidKrossRiveAnimationState(view)
        when (val res = config.resource) {
            is KrossRiveResource.Bytes -> state.load(res.data, config.stateMachine)
            is KrossRiveResource.Url -> state.load(res.url, config.stateMachine)
        }
        state
    }
}
