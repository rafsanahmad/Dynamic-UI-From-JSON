package com.rafsan.dynamicui_fromjson.utils

import android.os.Build
import android.text.Html
import android.view.View
import android.widget.LinearLayout

class Utils {
    companion object {
        fun fromHtml(str: String): String {
            return if (Build.VERSION.SDK_INT >= 24) Html.fromHtml(str, Html.FROM_HTML_MODE_LEGACY)
                .toString() else Html.fromHtml(str).toString()
        }

        fun setMerginToviews(view: View, topMergin: Int, width: Int, height: Int) {
            val layoutParams = LinearLayout.LayoutParams(width, height)
            layoutParams.setMargins(40, topMergin, 40, 0)
            view.layoutParams = layoutParams
        }
    }
}