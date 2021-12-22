package com.rafsan.dynamicui_fromjson.utils.dialog

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.Window
import android.view.WindowManager
import com.rafsan.dynamicui_fromjson.databinding.DialogLayoutBinding

class ShowDialog {
    companion object {
        fun customDialog(
            context: Context,
            title: String,
            message: String,
            listener: DialogListener?
        ) {
            val dialog = Dialog(context)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            val inflater =
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val binding: DialogLayoutBinding = DialogLayoutBinding.inflate(inflater)
            binding.modalTitle.text = title
            binding.modalMsg.text = message
            binding.modalConfirm.setOnClickListener {
                dialog.dismiss()
                listener?.OnSuccess("")
            }
            (context as Activity).window.setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE or
                        WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE
            )
            dialog.setContentView(binding.root)
            val window = dialog.window
            window!!.setLayout(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT
            )
            window.setGravity(Gravity.CENTER)
            dialog.show()
        }
    }
}