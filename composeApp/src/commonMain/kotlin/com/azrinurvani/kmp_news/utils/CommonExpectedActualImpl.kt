package com.azrinurvani.kmp_news.utils

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.azrinurvani.kmp_news.data.database.NewsDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.SynchronizedObject
import kotlinx.coroutines.internal.synchronized
import okio.Path.Companion.toPath

expect fun getType() : Type

expect fun getRandomId() : String

expect fun shareLink(url:String)

expect fun dataStorePreferences() : DataStore<Preferences>

object AppSettings{
    private lateinit var dataStore: DataStore<Preferences>

    @OptIn(InternalCoroutinesApi::class)
    private val lock = SynchronizedObject()

    @OptIn(InternalCoroutinesApi::class)
    fun getDataStore(producerPath: ()-> String): DataStore<Preferences>{
        return synchronized(lock){
            if(::dataStore.isInitialized){
                dataStore
            }else{
                PreferenceDataStoreFactory.createWithPath(
                    produceFile = {
                        producerPath().toPath()
                    }
                ).also {
                    dataStore = it
                }
            }
        }
    }
}

expect fun getDatabaseBuilder() : RoomDatabase.Builder<NewsDatabase>

fun getRoomDatabase(
    builder : RoomDatabase.Builder<NewsDatabase>
): NewsDatabase{
    return builder
        .setDriver(BundledSQLiteDriver()) //for get path of news_db stored from all platform (android,ios,desktop)
        .setQueryCoroutineContext(Dispatchers.IO)
        .build()
}