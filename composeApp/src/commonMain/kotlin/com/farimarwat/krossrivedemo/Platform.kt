package com.farimarwat.krossrivedemo

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform