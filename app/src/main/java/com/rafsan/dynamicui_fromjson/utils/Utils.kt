package com.rafsan.dynamicui_fromjson.utils

import android.os.Build
import android.text.Html

class Utils {
    companion object {
        fun fromHtml(str: String): String {
            return if (Build.VERSION.SDK_INT >= 24) Html.fromHtml(str, Html.FROM_HTML_MODE_LEGACY)
                .toString() else Html.fromHtml(str).toString()
        }
    }
}