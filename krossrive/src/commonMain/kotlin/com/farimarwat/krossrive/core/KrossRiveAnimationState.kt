package com.farimarwat.krossrive.core

import androidx.compose.runtime.Composable
import com.farimarwat.krossrive.model.KrossRiveAlignment
import com.farimarwat.krossrive.model.KrossRiveConfig
import com.farimarwat.krossrive.model.KrossRiveContentFit

interface KrossRiveAnimationState {
    fun play()
    fun pause()
    fun stop()
    fun reset()

    fun setBoolean(stateMachine: String, input: String, value: Boolean)
    fun setNumber(stateMachine: String, input: String, value: Float)
    fun fire(stateMachine: String, input: String)

    fun load(
        resource: ByteArray,
        artBoardName: String? = null,
        stateMachine: String? = null,
        autoPlay: Boolean = true,
        fit: KrossRiveContentFit = KrossRiveContentFit.COVER,
        alignment: KrossRiveAlignment = KrossRiveAlignment.CENTER
    )

    fun load(
        url: String,
        artBoardName: String? = null,
        stateMachine: String? = null,
        autoPlay: Boolean = true,
        fit: KrossRiveContentFit = KrossRiveContentFit.COVER,
        alignment: KrossRiveAlignment = KrossRiveAlignment.CENTER
    )
}

@Composable
expect fun rememberKrossRiveAnimationState(
    config: KrossRiveConfig
): KrossRiveAnimationState