package com.project.imperialcreation.userinfo.ViewModel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.project.imperialcreation.userinfo.Model.User

class SharedViewModel : ViewModel() {
    //  var item = MutableLiveData<ArrayList<String>>()
    var dataInsterted = MutableLiveData<Boolean>()
     var users = MutableLiveData<List<User>>()

    //  var item = MutableLiveData<String>()

    fun getInserted(): Boolean? {
        return dataInsterted.value
    }

    fun isDataInserted(item:Boolean) {
        this.dataInsterted.value = item
    }
    fun getUsers(): List<User>? {
        return users.value
    }

    fun setUsers(item:List<User>?) {
        this.users.value = item
    }

}