package com.project.imperialcreation.userinfo.AddingUsers

import android.content.Context
import android.widget.EditText
import com.project.imperialcreation.userinfo.DatabaseSetup.DataBaseInitializerPresnter
import com.project.imperialcreation.userinfo.DatabaseSetup.DatabaseClass
import com.project.imperialcreation.userinfo.ViewModel.SharedViewModel

class PresenterAddUser(db : DatabaseClass, model: SharedViewModel): DataBaseInitializerPresnter(model)
{
   fun validate(currentEditText : EditText): Boolean{
       if (currentEditText.text.toString().trim { it <= ' ' }.length > 0) {
           return  true
       }
       return  false // if it is empty
       }


}