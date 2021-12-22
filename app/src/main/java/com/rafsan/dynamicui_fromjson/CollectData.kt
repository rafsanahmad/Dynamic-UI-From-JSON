package com.rafsan.dynamicui_fromjson

import android.content.res.Resources
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.widget.SwitchCompat
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.rafsan.dynamicui_fromjson.GenerateFormActivity.Companion.submitPropertyArrayJson
import com.rafsan.dynamicui_fromjson.model.FormComponentItem
import com.rafsan.dynamicui_fromjson.model.Value
import java.util.ArrayList

class CollectData {
    companion object {
        /**
         * Get selected Date from date TextView.
         *
         * @param view
         * @param FormComponentItem
         */
        private fun getDataFromDateTextView(
            view: View,
            viewComponentModel: FormComponentItem
        ) {
            val dateView = view as TextView
            val submitPropertiesValueObj = JsonObject()
            submitPropertiesValueObj.addProperty("label", viewComponentModel.label)
            submitPropertiesValueObj.addProperty("value", dateView.text.toString())
            submitPropertiesValueObj.addProperty("type", viewComponentModel.type)
            submitPropertiesValueObj.addProperty("subtype", viewComponentModel.subtype))
            submitPropertyArrayJson.add(submitPropertiesValueObj)
        }

        /**
         * @param view
         * @param FormComponentItem
         * @return
         */
        private fun getDataFromCheckBoxGroup(
            view: View,
            viewComponentModel: FormComponentItem
        ): Boolean {
            return if (viewComponentModel.toggle != null && viewComponentModel.toggle) {
                getDataFromSwitchContainer(view, viewComponentModel)
            } else getDataFromCheckBoxContainer(view, viewComponentModel)
        }

        /**
         * @param view
         * @param FormComponentItem
         * @return
         */
        private fun getDataFromCheckBoxContainer(
            view: View,
            viewComponentModel: FormComponentItem
        ): Boolean {
            val checkBoxContainer = view as LinearLayout
            val submitJsonValues = JsonArray()
            val submitPropertiesValueObj = JsonObject()
            var valueModel: Value?
            return if (viewComponentModel.required != null && viewComponentModel.required) {
                for (i in 0 until checkBoxContainer.childCount) {
                    val submitJsonValue = JsonObject()
                    val checkBox = checkBoxContainer.getChildAt(i) as CheckBox
                    valueModel = viewComponentModel.values?.get(i)
                    if (checkBox.isChecked) {
                        submitJsonValue.addProperty("label", checkBox.text.toString())
                        //submitJsonValue.addProperty("value", valueModel.getValue());
                        if (valueModel != null) {
                            if (valueModel.label.equals("Other")) {
                                submitJsonValue.addProperty("value", valueModel.value)
                            } else submitJsonValue.addProperty("value", checkBox.text.toString())
                        }
                        submitJsonValues.add(submitJsonValue)
                    }
                }
                submitPropertiesValueObj.addProperty("label", viewComponentModel.label)
                submitPropertiesValueObj.add("value", submitJsonValues)
                submitPropertiesValueObj.addProperty("type", viewComponentModel.type)
                submitPropertiesValueObj.addProperty("subtype", viewComponentModel.subtype)
                submitPropertyArrayJson?.add(submitPropertiesValueObj)
                true
            } else {
                for (i in 0 until checkBoxContainer.childCount) {
                    val submitJsonValue = JsonObject()
                    val checkBox = checkBoxContainer.getChildAt(i) as CheckBox
                    valueModel = viewComponentModel.values?.get(i)
                    if (checkBox.isChecked) {
                        submitJsonValue.addProperty("label", checkBox.text.toString())
                        //submitJsonValue.addProperty("value", valueModel.getValue());
                        if (valueModel != null) {
                            if (valueModel.label.equals("Other")) {
                                submitJsonValue.addProperty("value", valueModel.value)
                            } else submitJsonValue.addProperty("value", checkBox.text.toString())
                        }
                        submitJsonValues.add(submitJsonValue)
                    }
                }
                submitPropertiesValueObj.addProperty("label", viewComponentModel.label)
                submitPropertiesValueObj.add("value", submitJsonValues)
                submitPropertiesValueObj.addProperty("type", viewComponentModel.type)
                submitPropertiesValueObj.addProperty("subtype", viewComponentModel.subtype)
                submitPropertyArrayJson?.add(submitPropertiesValueObj)
                true
            }
        }
    }
}