package com.azrinurvani.kmp_news.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.azrinurvani.kmp_news.data.model.Article

@Database(
    entities = [
        Article::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(SourceTypeConverter::class)
abstract class NewsDatabase : RoomDatabase(),DB{
    abstract fun newsDao() : NewsDao

    //Wajib di implement jikalau menggunakan Room Database untuk Multiplatform
    // dan penamaannya harus sama dengan yang ditulis yaitu clearAllTables()
    override fun clearAllTables() {
        super.clearAllTables()
    }
}

interface DB{
    fun clearAllTables() : Unit {}
}