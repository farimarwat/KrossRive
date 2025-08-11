package com.farimarwat.krossrive.model

import com.farimarwat.krossrive.model.KrossRiveAlignment
import com.farimarwat.krossrive.model.KrossRiveContentFit

data class KrossRiveConfig(
    val resource: KrossRiveResource,
    val stateMachine: String? = null,
    val artboard: String? = null,
    val autoPlay: Boolean = true,
    val fit: KrossRiveContentFit = KrossRiveContentFit.CONTAIN,
    val alignment: KrossRiveAlignment = KrossRiveAlignment.CENTER
)
