package com.farimarwat.krossrive.core

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.farimarwat.krossrive.utils.toAndroid

@Composable
actual fun KrossRiveAnimation(
    state: KrossRiveAnimationState,
    modifier: Modifier,
    autoPlay: Boolean,
    fit: KrossRiveContentFit,
    alignment: KrossRiveAlignment
) {
    AndroidView(
        factory = { context ->
            (state as AndroidKrossRiveAnimationState).view.apply {
                if (autoPlay) controller.play()
                this.fit = fit.toAndroid()
                this.alignment = alignment.toAndroid()
            }
        },
        modifier = modifier
    )
}