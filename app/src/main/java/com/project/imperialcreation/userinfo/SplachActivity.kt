package com.project.imperialcreation.userinfo

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.ActivityOptionsCompat

class SplachActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splach)
        waitSomeTime()
    }
   fun waitSomeTime(){
       Handler().postDelayed({
           //  startActivity(Intent(this,MyMainActivity::class.java))
           val bundle = ActivityOptionsCompat.makeCustomAnimation(this,
                   android.R.anim.fade_in, android.R.anim.fade_out).toBundle()
           startActivity(Intent(this,MainActivity::class.java), bundle)
           finish()


       }, 1800)
   }
}
