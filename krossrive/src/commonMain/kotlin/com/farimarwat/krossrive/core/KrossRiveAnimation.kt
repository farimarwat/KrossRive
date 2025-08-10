package com.farimarwat.krossrive.core

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
expect fun KrossRiveAnimation(
    state: KrossRiveAnimationState,
    modifier: Modifier = Modifier,
    autoPlay: Boolean = true,
    fit: KrossRiveContentFit = KrossRiveContentFit.CONTAIN,
    alignment: KrossRiveAlignment = KrossRiveAlignment.CENTER
)