package com.azrinurvani.kmp_news.utils

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import platform.Foundation.NSUUID
import platform.UIKit.*
import platform.Foundation.NSUUID
import platform.Foundation.NSURL
import platform.Foundation.NSUserDomainMask
import com.azrinurvani.kmp_news.utils.dataStoreFileName


actual fun shareLink(url: String) {
    val currentViewController = UIApplication.sharedApplication().keyWindow?.rootViewController
    val activityViewController = UIActivityViewController(listOf(url), null)
    currentViewController?.presentViewController(
        viewControllerToPresent = activityViewController,
        animated = true,
        completion = null
    )
}

actual fun getType(): Type {
    return Type.Mobile
}

actual fun randomUUIDStr(): String {
    return NSUUID().UUIDString()
}

actual fun getRandomId(): String {
    return NSUUID().UUIDString()
}

actual fun dataStorePreferences(): DataStore<Preferences> {
    return AppSettings.getDataStore(
        producerPath = {
            val documentDirectory : NSURL? = NSFileManager.defaultManager.UrlForDirectory(
                directory = NSDocumentDirectory,
                inDomain = NSUserDomainMask,
                appropriateForURL = null,
                create = false,
                error = null
            )
            requireNotNull(documentDirectory).path + "/$dataStoreFileName"
        }
    )
}