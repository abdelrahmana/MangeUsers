package com.project.imperialcreation.userinfo.Model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

 // model class for room table name and columns
@Entity(tableName = "usertable")
class User {

  @PrimaryKey(autoGenerate = true)
  var userid = 0
   @ColumnInfo(name = "user_name")
    //@PrimaryKey (autoGenerate = true)
    var userName: String = ""

     @ColumnInfo(name = "kind")
             //@PrimaryKey (autoGenerate = true)
     var userKind: String = ""

    @ColumnInfo(name = "job_title")
    var jobTitle: String? = null

    @ColumnInfo(name = "age")
    var age: Int = 0

}