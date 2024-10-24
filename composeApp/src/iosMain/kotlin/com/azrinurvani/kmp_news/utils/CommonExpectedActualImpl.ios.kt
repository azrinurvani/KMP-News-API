package com.azrinurvani.kmp_news.utils

import platform.Foundation.NSUUID

actual fun getType(): Type {
    return Type.Mobile
}

actual fun randomUUIDStr(): String {
    return NSUUID().UUIDString()
}