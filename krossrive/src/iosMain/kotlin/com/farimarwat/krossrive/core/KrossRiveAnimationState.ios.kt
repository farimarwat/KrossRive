package com.farimarwat.krossrive.core


import androidx.compose.runtime.Composable
import com.farimarwat.krossrive.model.KrossRiveConfig

import androidx.compose.runtime.remember
import com.farimarwat.krossrive.model.KrossRiveResource
import kotlinx.cinterop.BetaInteropApi
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.addressOf
import kotlinx.cinterop.usePinned
import platform.Foundation.NSData
import platform.Foundation.create

import RiveRuntime.*
import com.farimarwat.krossrive.model.KrossRiveAlignment
import com.farimarwat.krossrive.model.KrossRiveContentFit
import com.farimarwat.krossrive.utils.toIOS
import platform.posix.err

@OptIn(ExperimentalForeignApi::class)
class IosKrossRiveAnimationState(
    var config: KrossRiveConfig
) : KrossRiveAnimationState {

    var riveViewModel: RiveViewModel? = null
    override fun play() {

    }

    override fun pause() {
        riveViewModel?.pause()
    }

    override fun stop() {
        riveViewModel?.stop()
    }

    override fun reset() {
        riveViewModel?.reset()
    }
    override fun setBoolean(stateMachineName:String,input: String, value: Boolean) {
        riveViewModel?.setBooleanInput(input,value)
    }

    override fun setNumber(stateMachineName:String,input: String, value: Float) {
        riveViewModel?.setFloatInput(input,value)
    }

    override fun fire(stateMachineName:String,input: String) {
        riveViewModel?.triggerInput(input)
    }

    @OptIn(BetaInteropApi::class)
    override fun load(resource: ByteArray,
                      artBoardName:String?,
                      stateMachine: String?,
                      autoPlay:Boolean ,
                      fit: KrossRiveContentFit,
                      alignment: KrossRiveAlignment) {
        val nsData = resource.usePinned { pinned ->
            NSData.create(bytes = pinned.addressOf(0), length = resource.size.toULong())
        }

        val riveFile = RiveFile(data = nsData, loadCdn = false, error = null)
        val model = RiveModel(riveFile = riveFile)
        riveViewModel = RiveViewModel(
            model = model,
            animationName = null,
            fit = config.fit.toIOS(),
            alignment = config.alignment.toIOS(),
            autoPlay = config.autoPlay,
            artboardName = config.artboard
        )
    }

    override fun load(url: String,
                      artBoardName:String?,
                      stateMachine: String?,
                      autoPlay:Boolean ,
                      fit: KrossRiveContentFit,
                      alignment: KrossRiveAlignment) {
        riveViewModel= RiveViewModel(
            webURL = url,
            loadCdn = false,
            animationName = null,
            fit = config.fit.toIOS(),
            alignment = config.alignment.toIOS(),
            autoPlay = config.autoPlay,
            artboardName = config.artboard
        )

    }
}

@OptIn(ExperimentalForeignApi::class, BetaInteropApi::class)
@Composable
actual fun rememberKrossRiveAnimationState(config: KrossRiveConfig): KrossRiveAnimationState {
    return remember(config) {
        when(val resource = config.resource){
            is KrossRiveResource.Bytes -> {

                IosKrossRiveAnimationState(config).apply {
                    load(resource.data)
                }
            }
            is KrossRiveResource.Url -> {

                IosKrossRiveAnimationState(config).apply {
                    load(resource.url)
                }
            }
        }
    }
}
