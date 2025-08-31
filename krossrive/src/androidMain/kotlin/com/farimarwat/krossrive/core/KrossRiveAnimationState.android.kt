package com.farimarwat.krossrive.core

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import app.rive.runtime.kotlin.RiveAnimationView
import com.farimarwat.krossrive.model.KrossRiveAlignment
import com.farimarwat.krossrive.model.KrossRiveConfig
import com.farimarwat.krossrive.model.KrossRiveContentFit
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

    override fun setBoolean(stateMachineName:String, input: String, value: Boolean) {
        view.setBooleanState(stateMachineName, input, value)
    }

    override fun setNumber(stateMachineName:String,input: String, value: Float) {
        view.setNumberState(stateMachineName, input, value)
    }

    override fun fire(stateMachineName:String,input: String) {
        view.fireState(stateMachineName, input)
    }

    override fun load(
        resource: ByteArray,
        artBoardName: String?,
        stateMachine: String?,
        autoPlay: Boolean,
        fit: KrossRiveContentFit,
        alignment: KrossRiveAlignment
    ) {
        view.setRiveBytes(resource, stateMachine)
    }

    override fun load(
        url: String,
        artBoardName: String?,
        stateMachine: String?,
        autoPlay: Boolean,
        fit: KrossRiveContentFit,
        alignment: KrossRiveAlignment
    ) {

    }
}

@Composable
actual fun rememberKrossRiveAnimationState(
    config: KrossRiveConfig
): KrossRiveAnimationState {
    val context = LocalContext.current
    return remember(config) {
        val view = RiveAnimationView.Builder(context)
            .setAutoplay(config.autoPlay)
            .setFit(config.fit.toAndroid())
            .setAlignment(config.alignment.toAndroid())
            .also {
                if (config.artboard != null) {
                    it.setArtboardName(config.artboard)
                }
                if(config.stateMachine != null){
                    it.setStateMachineName(config.stateMachine)
                }
            }
            .build()
        val state = AndroidKrossRiveAnimationState(view)
        when (val res = config.resource) {
            is KrossRiveResource.Bytes -> state.load(res.data, config.stateMachine)
            is KrossRiveResource.Url -> state.load(res.url, config.stateMachine)
        }
        state
    }
}