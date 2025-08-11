package com.farimarwat.krossrive.utils

import com.farimarwat.krossrive.model.KrossRiveAlignment
import com.farimarwat.krossrive.model.KrossRiveContentFit

fun KrossRiveContentFit.toAndroid(): app.rive.runtime.kotlin.core.Fit = when (this) {
    KrossRiveContentFit.CONTAIN -> app.rive.runtime.kotlin.core.Fit.CONTAIN
    KrossRiveContentFit.COVER -> app.rive.runtime.kotlin.core.Fit.COVER
    KrossRiveContentFit.FILL -> app.rive.runtime.kotlin.core.Fit.FILL
    KrossRiveContentFit.SCALE_DOWN -> app.rive.runtime.kotlin.core.Fit.SCALE_DOWN
    KrossRiveContentFit.NONE -> app.rive.runtime.kotlin.core.Fit.NONE
}

fun KrossRiveAlignment.toAndroid(): app.rive.runtime.kotlin.core.Alignment = when (this) {
    KrossRiveAlignment.CENTER -> app.rive.runtime.kotlin.core.Alignment.CENTER
    KrossRiveAlignment.TOP_LEFT -> app.rive.runtime.kotlin.core.Alignment.TOP_LEFT
    KrossRiveAlignment.TOP_RIGHT -> app.rive.runtime.kotlin.core.Alignment.TOP_RIGHT
    KrossRiveAlignment.BOTTOM_LEFT -> app.rive.runtime.kotlin.core.Alignment.BOTTOM_LEFT
    KrossRiveAlignment.BOTTOM_RIGHT -> app.rive.runtime.kotlin.core.Alignment.BOTTOM_RIGHT
}