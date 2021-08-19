package com.planatech.bo.tracker.utils

import com.planatech.bo.tracker.BuildConfig

fun createImageUrl(url: String?): String {
    return BuildConfig.IMAGE_URL + url
}