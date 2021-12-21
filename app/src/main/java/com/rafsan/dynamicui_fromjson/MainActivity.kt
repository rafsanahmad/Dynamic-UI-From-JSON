package com.rafsan.dynamicui_fromjson

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.rafsan.dynamicui_fromjson.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val json = readRawJson()

        binding.buttonView.setOnClickListener {
            //Generate form
            if (json.isNotEmpty()) {
                val intent = Intent(this, GenerateFormActivity::class.java)
                intent.putExtra("value", json)
                startActivity(intent)
            }
        }
    }

    fun readRawJson(): String {
        val text = resources.openRawResource(R.raw.sample_json)
            .bufferedReader().use { it.readText() }
        val check = checkValidity(text)
        if (check) {
            binding.etContent.setText(text)
        } else {
            Snackbar.make(binding.content, "Invalid JSON", Snackbar.LENGTH_SHORT).show()
            return ""
        }
        return text
    }

    fun checkValidity(string: String): Boolean {
        val gson = Gson()
        try {
            gson.fromJson(string, Object::class.java)
        } catch (e: Exception) {
            return false
        }
        return true
    }
}