package com.azrinurvani.kmp_news.utils

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.room.Room
import androidx.room.RoomDatabase
import com.azrinurvani.kmp_news.data.database.NewsDatabase
import platform.Foundation.NSUUID
import platform.UIKit.*
import platform.Foundation.NSUUID
import platform.Foundation.NSURL
import platform.Foundation.NSUserDomainMask
import data.database.NewsDatabase
import data.database.instantiateImpl


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

actual fun getDatabaseBuilder(): RoomDatabase.Builder<NewsDatabase> {
    val dbFilePath = NSHomeDirectory() + "/$DB_NAME"
    return Room.databaseBuilder<NewsDatabase>(
        name = dbFilePath,
        factory =  { NewsDatabase::class.instantiateImpl() }
    )
}