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
    var riveViewModel: RiveViewModel?
) : KrossRiveAnimationState {

    override fun play() {

    }

    override fun pause() {

    }

    override fun stop() {

    }

    override fun reset() {

    }

    override fun setBoolean(stateMachine: String, input: String, value: Boolean) {

    }

    override fun setNumber(stateMachine: String, input: String, value: Float) {

    }

    override fun fire(stateMachine: String, input: String) {

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

        val riveFile = RiveFile(nsData,true,null)
        val model = RiveModel(riveFile)

    }

    override fun load(url: String,
                      artBoardName:String?,
                      stateMachine: String?,
                      autoPlay:Boolean ,
                      fit: KrossRiveContentFit,
                      alignment: KrossRiveAlignment) {

    }
}

@OptIn(ExperimentalForeignApi::class, BetaInteropApi::class)
@Composable
actual fun rememberKrossRiveAnimationState(config: KrossRiveConfig): KrossRiveAnimationState {
    return remember(config) {
        when(val resource = config.resource){
            is KrossRiveResource.Bytes -> {
                val nsData = resource.data.usePinned { pinned ->
                    NSData.create(bytes = pinned.addressOf(0), length = resource.data.size.toULong())
                }
                val riveFile = RiveFile(data = nsData, loadCdn = false, error = null)
                val model = RiveModel(riveFile = riveFile)
                val rVM = RiveViewModel(
                    model = model,
                    animationName = null,
                    fit = config.fit.toIOS(),
                    alignment = config.alignment.toIOS(),
                    autoPlay = config.autoPlay,
                    artboardName = config.artboard
                )
                IosKrossRiveAnimationState(rVM)
            }
            is KrossRiveResource.Url -> {
                val rVM = RiveViewModel(
                    webURL = resource.url,
                    loadCdn = false,
                    animationName = null,
                    fit = config.fit.toIOS(),
                    alignment = config.alignment.toIOS(),
                    autoPlay = config.autoPlay,
                    artboardName = config.artboard
                )
                IosKrossRiveAnimationState(rVM)
            }
        }
    }
}
