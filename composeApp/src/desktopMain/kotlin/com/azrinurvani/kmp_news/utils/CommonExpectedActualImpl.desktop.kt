package com.azrinurvani.kmp_news.utils

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.room.Room
import androidx.room.RoomDatabase
import com.azrinurvani.kmp_news.data.database.NewsDatabase
import java.awt.Toolkit
import java.awt.datatransfer.StringSelection
import java.io.File
import java.util.UUID

actual fun getType(): Type {
    return Type.Desktop
}

actual fun getRandomId(): String {
    return UUID.randomUUID().toString()
}

actual fun shareLink(url: String) {
    val clipboard = Toolkit.getDefaultToolkit().systemClipboard
    clipboard.setContents(StringSelection(url), null)
}

actual fun dataStorePreferences(): DataStore<Preferences> {
    return AppSettings.getDataStore {
        dataStoreFileName
    }
}

actual fun getDatabaseBuilder(): RoomDatabase.Builder<NewsDatabase> {
    val dbFile = File(System.getProperty("java.io.tmpdir"), DB_NAME)
    return Room.databaseBuilder<NewsDatabase>(
        name = dbFile.absolutePath
    ).fallbackToDestructiveMigration(false)
}