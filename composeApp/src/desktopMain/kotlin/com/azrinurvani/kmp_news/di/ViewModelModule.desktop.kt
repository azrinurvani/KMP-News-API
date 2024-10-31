package com.azrinurvani.kmp_news.di

import com.azrinurvani.kmp_news.ui.bookmark.BookmarkViewModel
import com.azrinurvani.kmp_news.ui.detail.ArticleDetailViewModel
import com.azrinurvani.kmp_news.ui.headline.HeadlineViewModel
import com.azrinurvani.kmp_news.ui.search.SearchViewModel
import com.azrinurvani.kmp_news.ui.setting.SettingViewModel
import org.koin.core.module.dsl.factoryOf

import org.koin.dsl.module

actual val viewModelModule = module {

    //use factory for handling viewModel recreates
    // is same with
//    factory{
//        HeadlineViewModel(get())
//    }
    factoryOf(::HeadlineViewModel)
    factoryOf(::SearchViewModel)
    factoryOf(::BookmarkViewModel)
    factoryOf(::ArticleDetailViewModel)
    factoryOf(::SettingViewModel)
}