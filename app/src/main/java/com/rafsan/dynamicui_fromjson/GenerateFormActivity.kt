package com.rafsan.dynamicui_fromjson

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.util.TypedValue
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.app.NavUtils
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.rafsan.dynamicui_fromjson.databinding.ActivityGenerateFormBinding
import com.rafsan.dynamicui_fromjson.model.FormComponent
import com.rafsan.dynamicui_fromjson.model.FormComponentItem
import com.rafsan.dynamicui_fromjson.model.FormViewComponent
import java.util.*

class GenerateFormActivity : AppCompatActivity() {

    lateinit var binding: ActivityGenerateFormBinding
    var formViewCollection: ArrayList<FormViewComponent> = arrayListOf()

    var submitRootJsonObj: JsonObject? = null
    var submitPropertyArrayJson: JsonArray? = null
    var formComponent: FormComponent? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGenerateFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val json = intent.getStringExtra("value")
        submitRootJsonObj = JsonObject()
        submitPropertyArrayJson = JsonArray()
        json?.let {
            populateForm(it)
        }
    }

    private fun populateForm(json: String) {
        formComponent = Gson().fromJson(json, FormComponent::class.java)
        var viewId = 1
        binding.miniAppFormContainer.setVisibility(View.VISIBLE)

        //TODO:- GENERATE FORM LAYOUT
        formComponent?.let {
            it.forEach { component ->
                when (component.type) {
                    "header" -> binding.miniAppFormContainer.addView(
                        createHeaderView(
                            component
                        )
                    )
                    "text" -> createEditableTextWithLabel(component, viewId++)
                    "textarea" -> createEditableTextWithLabel(component, viewId++)
                    "select" -> createSpinner(component, viewId++)
                    "radio-group" -> createRadioGroup(component, viewId++)
                    "paragraph" -> createParagraph(component)
                    "date" -> createDatePicker(component)
                    "checkbox-group" -> createCheckBoxGroup(component, viewId++)
                    "number" -> createNumberEditText(component)
                }
            }
        }
    }

    private fun createHeaderView(componentItem: FormComponentItem): TextView {
        val txtHeader = TextView(this)
        when (componentItem.subtype) {
            "h1" -> txtHeader.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20f)
            "h2" -> txtHeader.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18f)
            "h3" -> txtHeader.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16f)
        }

        txtHeader.setText(fromHtml(componentItem.label))

        txtHeader.layoutParams = LinearLayoutCompat.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        txtHeader.setTextColor(Color.parseColor("#000000"))
        txtHeader.setPadding(0, 15, 0, 15)
        txtHeader.gravity = Gravity.CENTER
        return txtHeader
    }


    private fun createSpinner(component: FormComponentItem, viewId: Int) {

    }

    private fun createDatePicker(component: FormComponentItem) {

    }

    private fun createNumberEditText(component: FormComponentItem) {

    }

    private fun createParagraph(component: FormComponentItem) {

    }

    private fun createCheckBoxGroup(component: FormComponentItem, viewId: Int) {

    }

    private fun createRadioGroup(component: FormComponentItem, viewId: Int) {

    }

    private fun createEditableTextWithLabel(component: FormComponentItem, viewId: Int) {

    }

    fun fromHtml(str: String): String {
        return if (Build.VERSION.SDK_INT >= 24) Html.fromHtml(str, Html.FROM_HTML_MODE_LEGACY)
            .toString() else Html.fromHtml(str).toString()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Respond to the action bar's Up/Home button
        if (item.itemId == R.id.home) {
            NavUtils.navigateUpFromSameTask(this)
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}