package com.farimarwat.krossrive.core

import androidx.compose.runtime.Composable
import com.farimarwat.krossrive.model.KrossRiveConfig

interface KrossRiveAnimationState {
    fun play()
    fun pause()
    fun stop()
    fun reset()

    fun setBoolean(stateMachine: String, input: String, value: Boolean)
    fun setNumber(stateMachine: String, input: String, value: Float)
    fun fire(stateMachine: String, input: String)

    fun load(resource: ByteArray, stateMachine: String? = null)
    fun load(url:String, stateMachine: String? = null)
}

@Composable
expect fun rememberKrossRiveAnimationState(
    config: KrossRiveConfig
): KrossRiveAnimationState