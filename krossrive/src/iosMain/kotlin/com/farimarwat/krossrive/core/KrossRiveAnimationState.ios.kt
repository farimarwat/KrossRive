package com.farimarwat.krossrive.core

import RiveRuntime.RiveFile
import androidx.compose.runtime.Composable
import com.farimarwat.krossrive.model.KrossRiveConfig


import RiveRuntime.RiveViewModel
import androidx.compose.runtime.remember
import com.farimarwat.krossrive.model.KrossRiveResource
import kotlinx.cinterop.BetaInteropApi
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.addressOf
import kotlinx.cinterop.usePinned
import platform.Foundation.NSData
import platform.Foundation.create



@OptIn(ExperimentalForeignApi::class)
class IosKrossRiveAnimationState(
     var riveViewModel: RiveViewModel
) : KrossRiveAnimationState {

    override fun play() {

    }

    override fun pause() {
        riveViewModel.pause()
    }

    override fun stop() {
        riveViewModel.stop()
    }

    override fun reset() {
        riveViewModel.reset()
    }

    override fun setBoolean(stateMachine: String, input: String, value: Boolean) {
        riveViewModel.setBooleanInput(input,value)
    }

    override fun setNumber(stateMachine: String, input: String, value: Float) {
        riveViewModel.setFloatInput(input,value)
    }

    override fun fire(stateMachine: String, input: String) {
        riveViewModel.triggerInput(input)
    }

    @OptIn(BetaInteropApi::class)
    override fun load(resource: ByteArray, stateMachine: String?) {
        val nsData = resource.usePinned { pinned ->
            NSData.create(bytes = pinned.addressOf(0), length = resource.size.toULong())
        }
    }

    override fun load(url: String, stateMachine: String?) {

    }
}

@OptIn(ExperimentalForeignApi::class)
@Composable
actual fun rememberKrossRiveAnimationState(config: KrossRiveConfig): KrossRiveAnimationState {
    return remember(config) {
        val riveViewModel = RiveViewModel()
        val state = IosKrossRiveAnimationState(riveViewModel)

        when (val res = config.resource) {
            is KrossRiveResource.Bytes -> state.load(res.data, config.stateMachine)
            is KrossRiveResource.Url -> state.load(res.url, config.stateMachine)
        }

        if (config.autoPlay) {
            state.play()
        }
        state
    }
}
