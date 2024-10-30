package com.azrinurvani.kmp_news.di

import com.azrinurvani.kmp_news.ui.bookmark.BookmarkViewModel
import com.azrinurvani.kmp_news.ui.detail.ArticleDetailViewModel
import com.azrinurvani.kmp_news.ui.headline.HeadlineViewModel
import com.azrinurvani.kmp_news.ui.search.SearchViewModel
import com.azrinurvani.kmp_news.ui.setting.SettingViewModel
import org.koin.core.module.dsl.singleOf

import org.koin.dsl.module

actual val viewModelModule = module {
    singleOf(::HeadlineViewModel)
    singleOf(::SearchViewModel)
    singleOf(::BookmarkViewModel)
    singleOf(::ArticleDetailViewModel)
    singleOf(::SettingViewModel)
}