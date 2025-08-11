package com.farimarwat.krossrive.core

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import app.rive.runtime.kotlin.RiveAnimationView
import com.farimarwat.krossrive.model.KrossRiveConfig
import com.farimarwat.krossrive.model.KrossRiveResource
import com.farimarwat.krossrive.utils.toAndroid

class AndroidKrossRiveAnimationState(
    val view: RiveAnimationView
) : KrossRiveAnimationState {

    override fun play() {
        view.play()
    }

    override fun pause() {
        view.pause()
    }

    override fun stop() {
        view.stop()
    }

    override fun reset() {
        view.reset()
    }

    override fun setBoolean(stateMachine: String, input: String, value: Boolean) {
        view.setBooleanState(stateMachine, input, value)
    }

    override fun setNumber(stateMachine: String, input: String, value: Float) {
        view.setNumberState(stateMachine, input, value)
    }

    override fun fire(stateMachine: String, input: String) {
        view.fireState(stateMachine, input)
    }

    override fun load(resource: ByteArray, stateMachine: String?) {
        view.setRiveBytes(resource, stateMachine)
    }

    override fun load(url: String, stateMachine: String?) {

    }
}

@Composable
actual fun rememberKrossRiveAnimationState(
    config: KrossRiveConfig
): KrossRiveAnimationState {
    val context = LocalContext.current
    return remember(config) {
        val view = RiveAnimationView(context).apply {
            fit = config.fit.toAndroid()
            alignment = config.alignment.toAndroid()
            autoplay = config.autoPlay
        }
        val state = AndroidKrossRiveAnimationState(view)
        when (val res = config.resource) {
            is KrossRiveResource.Bytes -> state.load(res.data, config.stateMachine)
            is KrossRiveResource.Url -> state.load(res.url, config.stateMachine)
        }
        state
    }
}