package com.farimarwat.krossrive.core

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.farimarwat.krossrive.model.KrossRiveAlignment
import com.farimarwat.krossrive.model.KrossRiveContentFit

@Composable
expect fun KrossRiveAnimation(
    state: KrossRiveAnimationState,
    modifier: Modifier = Modifier
)