package com.farimarwat.krossrive.utils

import RiveRuntime.RiveAlignment
import RiveRuntime.RiveFit
import com.farimarwat.krossrive.model.KrossRiveAlignment
import com.farimarwat.krossrive.model.KrossRiveContentFit
import kotlinx.cinterop.ExperimentalForeignApi

@OptIn(ExperimentalForeignApi::class)
 fun KrossRiveContentFit.toIOS(): RiveFit = when (this) {
    KrossRiveContentFit.CONTAIN -> RiveFit.contain
    KrossRiveContentFit.COVER -> RiveFit.cover
    KrossRiveContentFit.FILL -> RiveFit.fill
    KrossRiveContentFit.SCALE_DOWN -> RiveFit.scaleDown
    KrossRiveContentFit.NONE -> RiveFit.noFit
}

@OptIn(ExperimentalForeignApi::class)
 fun KrossRiveAlignment.toIOS(): RiveAlignment = when (this) {
    KrossRiveAlignment.CENTER -> RiveAlignment.center
    KrossRiveAlignment.TOP_LEFT -> RiveAlignment.topLeft
    KrossRiveAlignment.TOP_RIGHT -> RiveAlignment.topRight
    KrossRiveAlignment.BOTTOM_LEFT -> RiveAlignment.bottomLeft
    KrossRiveAlignment.BOTTOM_RIGHT -> RiveAlignment.bottomRight
}