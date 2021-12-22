package com.rafsan.dynamicui_fromjson.data

import android.view.View
import android.widget.*
import androidx.appcompat.widget.SwitchCompat
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.rafsan.dynamicui_fromjson.GenerateFormActivity.Companion.submitPropertyArrayJson
import com.rafsan.dynamicui_fromjson.model.FormComponentItem
import com.rafsan.dynamicui_fromjson.model.Value
import com.rafsan.dynamicui_fromjson.utils.Utils.Companion.isValidEmailAddress
import com.rafsan.dynamicui_fromjson.utils.Utils.Companion.isValidTelephoneNumber

class CollectData {
    companion object {
        /**
         * Get selected Date from date TextView.
         *
         * @param view
         * @param FormComponentItem
         */
        fun getDataFromDateTextView(
            view: View,
            viewComponentModel: FormComponentItem
        ) {
            val dateView = view as TextView
            val submitPropertiesValueObj = JsonObject()
            submitPropertiesValueObj.addProperty("label", viewComponentModel.label)
            submitPropertiesValueObj.addProperty("value", dateView.text.toString())
            submitPropertiesValueObj.addProperty("type", viewComponentModel.type)
            submitPropertiesValueObj.addProperty("subtype", viewComponentModel.subtype)
            submitPropertyArrayJson?.add(submitPropertiesValueObj)
        }

        /**
         * @param view
         * @param FormComponentItem
         * @return
         */
        fun getDataFromCheckBoxGroup(
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
        fun getDataFromCheckBoxContainer(
            view: View,
            viewComponentModel: FormComponentItem
        ): Boolean {
            val checkBoxContainer = view as LinearLayout
            val submitJsonValues = JsonArray()
            val submitPropertiesValueObj = JsonObject()
            var valueModel: Value?
            var isChecked = false
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
                        isChecked = true
                    }
                }
                submitPropertiesValueObj.addProperty("label", viewComponentModel.label)
                submitPropertiesValueObj.add("value", submitJsonValues)
                submitPropertiesValueObj.addProperty("type", viewComponentModel.type)
                submitPropertiesValueObj.addProperty("subtype", viewComponentModel.subtype)
                submitPropertyArrayJson?.add(submitPropertiesValueObj)
                return isChecked
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

        /**
         * @param view
         * @param FormComponentItem
         * @return
         */
        fun getDataFromSwitchContainer(
            view: View,
            viewComponentModel: FormComponentItem
        ): Boolean {
            val checkBoxContainer = view as LinearLayout
            val submitJsonValues = JsonArray()
            val submitPropertiesValueObj = JsonObject()
            var valueModel: Value?
            var isChecked = false
            return if (viewComponentModel.required != null && viewComponentModel.required) {
                for (i in 0 until (viewComponentModel.values?.size ?: 0)) {
                    val submitJsonValue = JsonObject()
                    val aSwitch = checkBoxContainer.getChildAt(i) as SwitchCompat
                    valueModel = viewComponentModel.values?.get(i)
                    if (aSwitch.isChecked) {
                        submitJsonValue.addProperty("label", aSwitch.text.toString())
                        //submitJsonValue.addProperty("value", valueModel.getValue());
                        if (valueModel != null) {
                            if (valueModel.label.equals("Other")) {
                                submitJsonValue.addProperty("value", valueModel.value)
                            } else submitJsonValue.addProperty("value", aSwitch.text.toString())
                        }
                        submitJsonValues.add(submitJsonValue)
                        isChecked = true
                    }
                }
                submitPropertiesValueObj.addProperty("label", viewComponentModel.label)
                submitPropertiesValueObj.add("value", submitJsonValues)
                submitPropertiesValueObj.addProperty("type", viewComponentModel.type)
                submitPropertiesValueObj.addProperty("subtype", viewComponentModel.subtype)
                submitPropertyArrayJson?.add(submitPropertiesValueObj)
                return isChecked
            } else {
                for (i in 0 until (viewComponentModel.values?.size ?: 0)) {
                    val submitJsonValue = JsonObject()
                    val aSwitch = checkBoxContainer.getChildAt(i) as SwitchCompat
                    valueModel = viewComponentModel.values?.get(i)
                    if (aSwitch.isChecked) {
                        submitJsonValue.addProperty("label", aSwitch.text.toString())
                        //submitJsonValue.addProperty("value", valueModel.getValue());
                        if (valueModel != null) {
                            if (valueModel.label.equals("Other")) {
                                submitJsonValue.addProperty("value", valueModel.value)
                            } else submitJsonValue.addProperty("value", aSwitch.text.toString())
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

        /**
         * @param view
         * @param FormComponentItem
         * @return Boolean value to check required field fill up or not
         */
        fun getDataFromEditText(
            view: View,
            viewComponentModel: FormComponentItem
        ): Boolean {
            val editText = view as EditText
            val submitPropertiesValueObj = JsonObject()
            if (editText.text.toString() != "") {
                viewComponentModel.required?.let {
                    //Not null
                    if (it) {
                        if (editText.text.toString() == "") {
                            return false
                        } else {
                            viewComponentModel.subtype?.let { subType ->
                                if (subType.equals("tel")) {
                                    if (!isValidTelephoneNumber(editText.text.toString())) {
                                        return false
                                    }
                                } else if (subType.equals("email")) {
                                    if (!isValidEmailAddress(editText.text.toString())) {
                                        return false
                                    }
                                }
                            }
                        }
                        submitPropertiesValueObj.addProperty(
                            "label",
                            viewComponentModel.label
                        )
                        submitPropertiesValueObj.addProperty("value", editText.text.toString())
                        submitPropertiesValueObj.addProperty(
                            "type",
                            viewComponentModel.type
                        )
                        submitPropertiesValueObj.addProperty(
                            "subtype",
                            viewComponentModel.subtype
                        )
                        submitPropertyArrayJson!!.add(submitPropertiesValueObj)
                    }
                }
                submitPropertiesValueObj.addProperty("label", viewComponentModel.label)
                submitPropertiesValueObj.addProperty("value", editText.text.toString())
                submitPropertiesValueObj.addProperty("type", viewComponentModel.type)
                submitPropertiesValueObj.addProperty("subtype", viewComponentModel.subtype)
                submitPropertyArrayJson!!.add(submitPropertiesValueObj)
            }
            return true
        }

        /**
         * @param view
         * @param FormComponentItem
         * @return
         */
        fun getDataFromRadioGroup(
            view: View,
            viewComponentModel: FormComponentItem
        ): Boolean {
            return true
        }

        /**
         * @param view
         * @param FormComponentItem
         * @return
         */
        fun getDataFromSpinner(
            view: View,
            viewComponentModel: FormComponentItem
        ): Boolean {
            return true
        }
    }
}