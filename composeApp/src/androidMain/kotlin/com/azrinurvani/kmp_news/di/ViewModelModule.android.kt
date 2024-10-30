package com.azrinurvani.kmp_news.di

import com.azrinurvani.kmp_news.ui.bookmark.BookmarkViewModel
import com.azrinurvani.kmp_news.ui.detail.ArticleDetailViewModel
import com.azrinurvani.kmp_news.ui.headline.HeadlineViewModel
import com.azrinurvani.kmp_news.ui.search.SearchViewModel
import com.azrinurvani.kmp_news.ui.setting.SettingViewModel
import org.koin.core.module.dsl.viewModelOf

import org.koin.dsl.module

actual val viewModelModule = module {

    viewModelOf(::HeadlineViewModel)
    viewModelOf(::SearchViewModel)
    viewModelOf(::BookmarkViewModel)
    viewModelOf(::ArticleDetailViewModel)
    viewModelOf(::SettingViewModel)
}