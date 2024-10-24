package com.azrinurvani.kmp_news.utils

import java.util.UUID
import kotlin.uuid.Uuid

actual fun getType(): Type {
    return Type.Mobile
}

actual fun getRandomId(): String {
    return UUID.randomUUID().toString()
}