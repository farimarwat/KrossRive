package com.farimarwat.krossrive.core

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.farimarwat.krossrive.model.KrossRiveAlignment
import com.farimarwat.krossrive.model.KrossRiveContentFit
import com.farimarwat.krossrive.utils.toAndroid

@Composable
actual fun KrossRiveAnimation(
    state: KrossRiveAnimationState,
    modifier: Modifier
) {
    AndroidView(
        factory = { context ->
            (state as AndroidKrossRiveAnimationState).view
        },
        modifier = modifier
    )
}