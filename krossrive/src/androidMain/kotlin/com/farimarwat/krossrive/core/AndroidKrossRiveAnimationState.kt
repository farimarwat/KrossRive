package com.farimarwat.krossrive.core

import app.rive.runtime.kotlin.RiveAnimationView

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