package com.rafsan.dynamicui_fromjson.data

import android.util.Log
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
import java.util.*

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
            val json = populateSubmitPropertyJson(viewComponentModel, null)
            json.addProperty("value", dateView.text.toString())
            submitPropertyArrayJson?.add(json)
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
                            if (valueModel.label == "Other") {
                                submitJsonValue.addProperty("value", valueModel.value)
                            } else submitJsonValue.addProperty("value", checkBox.text.toString())
                        }
                        submitJsonValues.add(submitJsonValue)
                        isChecked = true
                    }
                }
                val json = populateSubmitPropertyJson(viewComponentModel, null)
                json.add("value", submitJsonValues)
                submitPropertyArrayJson?.add(json)
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
                val json = populateSubmitPropertyJson(viewComponentModel, null)
                json.add("value", submitJsonValues)
                submitPropertyArrayJson?.add(json)
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
                            if (valueModel.label == "Other") {
                                submitJsonValue.addProperty("value", valueModel.value)
                            } else submitJsonValue.addProperty("value", aSwitch.text.toString())
                        }
                        submitJsonValues.add(submitJsonValue)
                        isChecked = true
                    }
                }
                val json = populateSubmitPropertyJson(viewComponentModel, null)
                json.add("value", submitJsonValues)
                submitPropertyArrayJson?.add(json)
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
                            if (valueModel.label == "Other") {
                                submitJsonValue.addProperty("value", valueModel.value)
                            } else submitJsonValue.addProperty("value", aSwitch.text.toString())
                        }
                        submitJsonValues.add(submitJsonValue)
                    }
                }
                val json = populateSubmitPropertyJson(viewComponentModel, null)
                json.add("value", submitJsonValues)
                submitPropertyArrayJson?.add(json)
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
            if (editText.text.toString() != "") {
                viewComponentModel.required?.let {
                    //Not null
                    if (it) {
                        if (editText.text.toString() == "") {
                            return false
                        } else {
                            viewComponentModel.subtype?.let { subType ->
                                if (subType == "tel") {
                                    if (!isValidTelephoneNumber(editText.text.toString())) {
                                        return false
                                    }
                                } else if (subType == "email") {
                                    if (!isValidEmailAddress(editText.text.toString())) {
                                        return false
                                    }
                                }
                            }
                        }
                    }
                }
                val json = populateSubmitPropertyJson(viewComponentModel, null)
                json.addProperty("value", editText.text.toString())
                submitPropertyArrayJson?.add(json)
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
            val radioGroup = view as RadioGroup
            val valuesModels: ArrayList<Value> = viewComponentModel.values as ArrayList<Value>
            val valueModel: Value
            val radioButton: RadioButton
            val radioButtonIndex: Int
            return if (viewComponentModel.required != null && viewComponentModel.required) {
                if (radioGroup.checkedRadioButtonId != -1) {
                    radioButton =
                        radioGroup.findViewById<View>(radioGroup.checkedRadioButtonId) as RadioButton
                    radioButtonIndex = radioGroup.indexOfChild(radioButton)
                    valueModel = valuesModels[radioButtonIndex]
                    val json = populateSubmitPropertyJson(viewComponentModel, valueModel)
                    submitPropertyArrayJson?.add(json)
                    Log.i("selectedRadioButton", valueModel.label)
                    true
                } else {
                    false
                }
            } else {
                if (radioGroup.checkedRadioButtonId != -1) {
                    radioButton =
                        radioGroup.findViewById<View>(radioGroup.checkedRadioButtonId) as RadioButton
                    radioButtonIndex = radioGroup.indexOfChild(radioButton)
                    valueModel = valuesModels[radioButtonIndex]
                    val json = populateSubmitPropertyJson(viewComponentModel, valueModel)
                    submitPropertyArrayJson?.add(json)
                    Log.i("selectedRadioButton", valueModel.label)
                }
                true
            }
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
            val spinner = view as Spinner

            if (viewComponentModel.required != null && viewComponentModel.required) {
                return if (spinner.selectedItem != null) {
                    val json = populateSubmitPropertyJson(viewComponentModel, null)
                    val selectedValues = populateSpinnerOptions(viewComponentModel, spinner)
                    json.add("value", selectedValues)
                    submitPropertyArrayJson?.add(json)
                    Log.i("SpinnerSelectedItem", spinner.selectedItem.toString())
                    true
                } else {
                    false
                }
            } else {
                if (spinner.selectedItem != null) {
                    val json = populateSubmitPropertyJson(viewComponentModel, null)
                    val selectedValues = populateSpinnerOptions(viewComponentModel, spinner)
                    json.add("value", selectedValues)
                    submitPropertyArrayJson?.add(json)
                    Log.i("SpinnerSelectedItem", spinner.selectedItem.toString())
                    return true
                }
            }

            return true
        }

        fun populateSpinnerOptions(componentItem: FormComponentItem, spinner: Spinner): JsonArray {
            val selectedValue = JsonObject()
            val selectedValues = JsonArray()
            selectedValue.addProperty("label", spinner.selectedItem.toString())
            if (spinner.selectedItem.toString() == "Other") {
                selectedValue.addProperty(
                    "value",
                    componentItem.values
                        ?.get(spinner.selectedItemPosition)?.value
                )
            } else {
                selectedValue.addProperty(
                    "value",
                    spinner.selectedItem.toString()
                )
            }
            selectedValues.add(selectedValue)
            return selectedValues
        }

        fun populateSubmitPropertyJson(
            componentItem: FormComponentItem,
            valueModel: Value?
        ): JsonObject {
            val submitPropertiesValueObj = JsonObject()
            submitPropertiesValueObj.addProperty("label", componentItem.label)
            valueModel?.let {
                if (it.label == "Other") {
                    submitPropertiesValueObj.addProperty("value", it.value)
                } else submitPropertiesValueObj.addProperty("value", it.label)
            }
            submitPropertiesValueObj.addProperty("type", componentItem.type)
            submitPropertiesValueObj.addProperty("subtype", componentItem.subtype)
            return submitPropertiesValueObj
        }
    }
}