package com.project.imperialcreation.userinfo

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityOptionsCompat
import android.view.animation.AnimationUtils
import com.project.imperialcreation.userinfo.CustomAnimationPackage.MyBounceInterpolator
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bounceLogoAnimation() // make animation bounce
        eventListener() // listen buttons

    }

    private fun eventListener() {
        val bundle = ActivityOptionsCompat.makeCustomAnimation(this,
                android.R.anim.fade_in, android.R.anim.fade_out).toBundle()
        createNewUser.setOnClickListener({
            startActivity(Intent(this,ContainerUsersActivity::class.java).putExtra(currentLayoutHolder, addingUsers),bundle)
        })
        showCurrentUsers.setOnClickListener({
            startActivity(Intent(this,ContainerUsersActivity::class.java).putExtra(currentLayoutHolder, loadUsers),bundle)

        })
    }

    private fun bounceLogoAnimation() {
        val myAnim = AnimationUtils.loadAnimation(this,R.anim.animation)

        // Use bounce interpolator with amplitude 0.2 and frequency 20
        val interpolator = MyBounceInterpolator(0.5, 10.0)
        myAnim.interpolator = interpolator
        logoMain.startAnimation(myAnim)
    }
    companion object {
        val currentLayoutHolder = "which_one"
        val addingUsers = "add_fragment"
        val loadUsers = "load_fragment"
    }
}
