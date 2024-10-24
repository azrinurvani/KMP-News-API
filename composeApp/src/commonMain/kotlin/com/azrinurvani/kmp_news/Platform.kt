package com.azrinurvani.kmp_news

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform