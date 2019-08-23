package com.project.imperialcreation.userinfo

import android.app.ProgressDialog
import android.content.Context

class ProgressDialog(val context: Context) {
   private var dialog : ProgressDialog? = null
    fun setDialog( show : Boolean, message : String){

        if (show)
        {
            if (dialog?.isShowing == true) return
            dialog = ProgressDialog.show(context, "", message)
            dialog?.setCancelable(false)
        }
        else{
            dialog?.dismiss()
        }

    }
    fun changeDialogMessage(message: String){
        if (dialog==null){
            dialog = ProgressDialog.show(context, "", message)


        }else
            dialog!!.setMessage(message)
    }

}