package com.ertugrulakkaya.spaceexplorer.domain.util

import android.content.Context
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

object AndroidContextRetriever : KoinComponent {
    val context : Context get() = get()
}