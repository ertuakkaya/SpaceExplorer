package com.ertugrulakkaya.spaceexplorer.domain.util

import platform.Foundation.NSURL
import platform.UIKit.UIApplication

actual fun openUrl(url: String) {
    val nsUrl = NSURL.URLWithString(url)
    if (nsUrl != null){
        UIApplication.sharedApplication.openURL(
            url = nsUrl,
            options = emptyMap<Any?, Any?>(),
            completionHandler = null
        )
    }
}