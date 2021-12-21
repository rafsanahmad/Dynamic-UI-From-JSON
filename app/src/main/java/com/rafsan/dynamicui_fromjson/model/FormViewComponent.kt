package com.rafsan.dynamicui_fromjson.model

import android.view.View

class FormViewComponent(
    val createdView: View,
    viewComponentModel: FormComponentItem
) {
    private val viewComponentModel: FormComponentItem = viewComponentModel
    fun getViewComponentModel(): FormComponentItem {
        return viewComponentModel
    }

}