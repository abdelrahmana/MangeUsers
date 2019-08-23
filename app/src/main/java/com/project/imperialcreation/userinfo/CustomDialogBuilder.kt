package com.project.imperialcreation.userinfo

import android.app.Dialog
import android.content.Context
import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView

object CustomDialogBuilder{
    var dialog : Dialog? = null


    fun dialogBuilder(message : String,error : Boolean,context: Context) {
         dialog = Dialog(context)
        dialog!!.setContentView(R.layout.dialog_message)
        val textViewmessage  = dialog!!.findViewById<TextView>(R.id.dialog_message_toast)
        textViewmessage.text = message
        // dialogCustomDatePicker.setTitle("جوائز ممكن تكسبها")
        val btnClose = dialog!!.findViewById<TextView>(R.id.confirm_close)
        val errorImage = dialog!!.findViewById<RelativeLayout>(R.id.relativeAttention)
        if (error)
            errorImage.visibility = View.VISIBLE
        else
            errorImage.visibility = View.GONE

        btnClose.setOnClickListener({
            dialog!!.dismiss()
        })
        if (dialog!!.isShowing)
            dialog!!.dismiss()
        dialog!!.show()
        dialog!!.getWindow().setBackgroundDrawableResource(android.R.color.transparent)
    }
}