package com.ertugrulakkaya.spaceexplorer

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform