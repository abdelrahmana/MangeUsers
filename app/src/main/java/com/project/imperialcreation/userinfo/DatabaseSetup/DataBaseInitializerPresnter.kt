package com.project.imperialcreation.userinfo.DatabaseSetup

import android.os.AsyncTask
import com.project.imperialcreation.userinfo.Model.User
import com.project.imperialcreation.userinfo.ViewModel.SharedViewModel


open class DataBaseInitializerPresnter(val model : SharedViewModel) {

    fun populateAsync(db: DatabaseClass,user : User, filter: String) {
        val task = PopulateDbAsync(db,this,user, filter)
        task.execute()
    }
    fun populateAsync(db : DatabaseClass,filter: String) {
        val task = PopulateDbAsync(db,this,User(), filter)
        task.execute()
    }

    private fun addUser(db: DatabaseClass, user: User): User {
        db.userDao().insert(user)
        return user
    }
    var userList : List<User>? = null
   private  fun getUsers(db: DatabaseClass) {

        userList = db.userDao().getUsers()//.getAllUsers
    }

    class PopulateDbAsync internal constructor(private val mDb: DatabaseClass,
                                               val dataBaseInitializerPresnter: DataBaseInitializerPresnter,
                                               val user: User,val filter: String) : AsyncTask<String, String, String>() {

        override fun onPostExecute(result: String) {
            super.onPostExecute(result)
            if (filter.equals(insert)){
                if (result.equals(success))
                    dataBaseInitializerPresnter.model.isDataInserted(true)

                else
                    dataBaseInitializerPresnter.model.isDataInserted(false)// when error happens



            }else{ // if filter get users
                if (result.equals(success)){
                    dataBaseInitializerPresnter.model.setUsers(dataBaseInitializerPresnter.userList)

                }else {
                    dataBaseInitializerPresnter.model.setUsers(null)// when error happens

                }
            }

        }

        override fun doInBackground(vararg p0: String): String{
            when(filter){
                insert->{
                 return  doInsertOperation()
                } getUsers->{
              return getUsers()
            }

            }

            return failure
        }

        private fun doInsertOperation(): String {
            try {

                dataBaseInitializerPresnter.addUser(mDb, user)
                return success

            }catch (e :Exception){
                return failure
            }
        }

        private fun getUsers(): String {
            try {

              dataBaseInitializerPresnter.getUsers(mDb)
              return success

            }catch (e :Exception){

                return failure
            }
        }

    }
    companion object {
        val insert = "insert"
        val getUsers = "get_users"
        val success = "Success"
        val failure = "failure"
    }
}