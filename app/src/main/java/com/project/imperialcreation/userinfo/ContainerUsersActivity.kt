package com.project.imperialcreation.userinfo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import com.project.imperialcreation.userinfo.AddingUsers.AddingUserFragment
import com.project.imperialcreation.userinfo.LoadingUsers.LoadingUsers
import com.project.imperialcreation.userinfo.MainActivity.Companion.addingUsers
import com.project.imperialcreation.userinfo.MainActivity.Companion.currentLayoutHolder
import com.project.imperialcreation.userinfo.MainActivity.Companion.loadUsers
import com.project.imperialcreation.userinfo.R

class ContainerUsersActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adding_users)
        loadFrament()
    }

    private fun loadFrament() {
        val whichFragment = intent.getStringExtra(currentLayoutHolder)
        when (whichFragment){
            addingUsers -> {
                changeFragment(AddingUserFragment())

            } loadUsers->{
            changeFragment(LoadingUsers())



        }

        }
    }

    fun changeFragment(targetFragment: Fragment) {
        try {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.container, targetFragment, "fragment")
                    .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                    .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    .commit()
        }catch (e : Exception){

        }


    }
}
