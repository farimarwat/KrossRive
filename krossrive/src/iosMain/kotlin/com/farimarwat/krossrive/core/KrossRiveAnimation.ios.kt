package com.farimarwat.krossrive.core


import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.UIKitView
import com.farimarwat.krossrive.model.KrossRiveAlignment
import com.farimarwat.krossrive.model.KrossRiveContentFit
import kotlinx.cinterop.ExperimentalForeignApi
import platform.UIKit.UIView

@OptIn(ExperimentalForeignApi::class)
@Composable
actual fun KrossRiveAnimation(
    state: KrossRiveAnimationState,
    modifier: Modifier
) {
    UIKitView(
        factory = {
            (state as IosKrossRiveAnimationState).riveViewModel?.createRiveView() as UIView
        },
        modifier = modifier
    )
}