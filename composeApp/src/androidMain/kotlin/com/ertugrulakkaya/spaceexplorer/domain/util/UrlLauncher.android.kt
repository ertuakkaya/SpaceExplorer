package com.ertugrulakkaya.spaceexplorer.domain.util

import android.content.Intent
import android.net.Uri
import org.koin.android.ext.koin.androidContext

actual fun openUrl(url: String) {
    val context = AndroidContextRetriever.context
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url)).apply {
        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    }
    context.startActivity(intent)
}
