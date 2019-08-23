package com.project.imperialcreation.userinfo.DatabaseSetup

import android.arch.lifecycle.LiveData
import com.project.imperialcreation.userinfo.Model.User
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query


@Dao
interface UserDao {

    @Query("SELECT * FROM usertable")
    fun getUsers(): List<User>

    /*@get:Query("SELECT * FROM user")
    val getAllUsers: ArrayList<User>*/

    @Query("SELECT COUNT(*) from usertable")
    fun countUsers(): Int

    @Insert
    fun insertAll(vararg users: User)

    @Insert
    fun  insert(user : User)


   /* @Delete
    fun delete(user: User)*/
}