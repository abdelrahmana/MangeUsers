package com.project.imperialcreation.userinfo.AddingUsers

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.LayerDrawable
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.widget.Button
import android.widget.TextView
import com.project.imperialcreation.userinfo.CustomDialogBuilder
import com.project.imperialcreation.userinfo.DatabaseSetup.DataBaseInitializerPresnter.Companion.insert
import com.project.imperialcreation.userinfo.DatabaseSetup.DatabaseClass
import com.project.imperialcreation.userinfo.Model.User
import com.project.imperialcreation.userinfo.ProgressDialog

import com.project.imperialcreation.userinfo.R
import com.project.imperialcreation.userinfo.ViewModel.SharedViewModel
import kotlinx.android.synthetic.main.fragment_adding_user.*

class AddingUserFragment : Fragment() {
    lateinit var btnAddNewUser: Button
    var db: DatabaseClass? = null
    var model: SharedViewModel? = null
    var presenter: PresenterAddUser? = null
    lateinit var progressDialog: ProgressDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_adding_user, container, false)
        btnAddNewUser = rootView.findViewById<Button>(R.id.btn_add)
        progressDialog = ProgressDialog(context!!)
        db = DatabaseClass.getAppDatabase(context!!)//invoke(activity!!)
        model = this.run {
            ViewModelProviders.of(activity!!)[SharedViewModel::class.java]
        }
        presenter = PresenterAddUser(db!!, model!!)
        setUserWhenSave()
        viewModelObserver()
        return rootView
    }

    private fun viewModelObserver() {
        model!!.dataInsterted.observe(this, Observer<Boolean> { taskSuccess ->
            progressDialog.setDialog(false, "") // hide dialog
            if (taskSuccess != null) {
                if (taskSuccess) {
                    CustomDialogBuilder.dialogBuilder(activity!!.resources.getString(R.string.successfully_added), false, activity!!)
                } else {
                    CustomDialogBuilder.dialogBuilder(activity!!.resources.getString(R.string.failed), false, activity!!)


                }

            } else {
                CustomDialogBuilder.dialogBuilder(activity!!.resources.getString(R.string.error), false, activity!!)

            }

        })
    }
    var selectedGender = "Man"
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        defultChooserMan()
        manLinear.setOnClickListener({
            setUpFadeAnimation(manTextView,girlTextView)
            selectedGender = "Man"
        })
        girlLinear.setOnClickListener({
            setUpFadeAnimation(girlTextView,manTextView)
            selectedGender = "Woman"
        })
    }

    private fun setUserWhenSave() {
        btnAddNewUser.setOnClickListener({
            if (presenter!!.validate(username) && presenter!!.validate(age) && presenter!!.validate(job_title_edittext)) {
                // if every thing is ok
                // lets
                progressDialog.setDialog(true,context!!.resources.getString(R.string.please_wait))
                presenter!!.populateAsync(db!!, buiLdUserInfo(username.text.toString().trim()
                        , job_title_edittext.text.toString().trim(), age.text.toString().trim()), insert)

            } else {
                showWhatIsWrong()
            }


        })
    }

    fun defultChooserMan() {
        val bottomBorder = getBorders(
                ContextCompat.getColor(context!!, R.color.white), // Background color
                ContextCompat.getColor(context!!, R.color.underline_color), // Border color
                0, // Left border in pixels
                0, // Top border in pixels
                0, // Right border in pixels
                10 // Bottom border in pixels
        )
        manTextView.background = bottomBorder
    }
    private fun buiLdUserInfo(userName: String, jobTitle: String, age: String): User {
        val user = User()
        user.userName = userName
        user.age = Integer.parseInt(age)
        user.jobTitle = jobTitle
        user.userKind = selectedGender
        return user
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)

    }

    override fun onDetach() {
        super.onDetach()
    }

    private fun showWhatIsWrong() { // this function is used when fragment is inflated
        if (username.text.isEmpty()) {
            CustomDialogBuilder.dialogBuilder(activity!!.resources.getString(R.string.write_yourname), true, activity!!)
            //  Toast.makeText(activity, "من فضلك أدخل الأسم الخاص بك", Toast.LENGTH_SHORT).show()

        } else if (age.text.isEmpty()) {
            CustomDialogBuilder!!.dialogBuilder(activity!!.resources.getString(R.string.write_age), true, activity!!)


        } else if (job_title_edittext.text.isEmpty()) {
            CustomDialogBuilder!!.dialogBuilder(activity!!.resources.getString(R.string.write_job_title), true, activity!!)

        }
    }

    fun setUpFadeAnimation(selectedTextView: TextView, removedBackgroundTextView: TextView) {

        val fadeOut: Animation = AlphaAnimation(1.0f, 0.0f)
        fadeOut.setDuration(200)
        fadeOut.setStartOffset(200)

        fadeOut.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationEnd(arg0: Animation) {
                val bottomBorder = getBorders(
                        ContextCompat.getColor(context!!, R.color.white), // Background color
                        ContextCompat.getColor(context!!, R.color.underline_color), // Border color
                        0, // Left border in pixels
                        0, // Top border in pixels
                        0, // Right border in pixels
                        10 // Bottom border in pixels
                )
                selectedTextView.background = bottomBorder
            }

            override fun onAnimationRepeat(arg0: Animation) {}

            override fun onAnimationStart(arg0: Animation) {
                removedBackgroundTextView.background = null


            }
        })
        selectedTextView.startAnimation(fadeOut)

    }

    fun getBorders(bgColor: Int, borderColor: Int,
                   left: Int, top: Int, right: Int, bottom: Int): LayerDrawable {
        // Initialize new color drawables
        val borderColorDrawable = ColorDrawable(borderColor)
        val backgroundColorDrawable = ColorDrawable(bgColor)
        val drawables = arrayOf<Drawable>(borderColorDrawable, backgroundColorDrawable)


        // Initialize a new layer drawable instance from drawables array
        val layerDrawable = LayerDrawable(drawables)

        // Set padding for background_revenue color layer
        layerDrawable.setLayerInset(
                1, // Index of the drawable to adjust [background_revenue color layer]
                left, // Number of pixels to add to the left bound [left border]
                top, // Number of pixels to add to the top bound [top border]
                right, // Number of pixels to add to the right bound [right border]
                bottom // Number of pixels to add to the bottom bound [bottom border]
        );

        // Finally, return the one or more sided bordered background_revenue drawable
        return layerDrawable
    }
}