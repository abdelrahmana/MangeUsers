package com.project.imperialcreation.userinfo.LoadingUsers

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.project.imperialcreation.userinfo.CustomDialogBuilder
import com.project.imperialcreation.userinfo.DatabaseSetup.DataBaseInitializerPresnter
import com.project.imperialcreation.userinfo.DatabaseSetup.DataBaseInitializerPresnter.Companion.getUsers
import com.project.imperialcreation.userinfo.DatabaseSetup.DatabaseClass
import com.project.imperialcreation.userinfo.Model.User
import com.project.imperialcreation.userinfo.ProgressDialog

import com.project.imperialcreation.userinfo.R
import com.project.imperialcreation.userinfo.ViewModel.SharedViewModel
import java.util.ArrayList

class LoadingUsers : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    lateinit var progressDialog : ProgressDialog
    var arrayListOfPlans : ArrayList<User> = ArrayList<User>()
    lateinit var recycleViewUsers: RecyclerView
    lateinit var recycleAdaptor : RecycleAdaptorUsers
    lateinit var noUsersTextView: TextView
    var db: DatabaseClass? = null
    var model: SharedViewModel? = null
        var presenter: DataBaseInitializerPresnter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_loading_users, container, false)
        progressDialog = ProgressDialog(activity!!)
        // national id of user
        setAllViews(view)
        db = DatabaseClass.getAppDatabase(context!!)//invoke(activity!!)
        model = this.run {
            ViewModelProviders.of(activity!!)[SharedViewModel::class.java]
        }
        progressDialog.setDialog(true,activity!!.resources.getString(R.string.please_wait))
        presenter = DataBaseInitializerPresnter(model!!)
        presenter!!.populateAsync(db!!,getUsers) // get users // overloading methods
        viewModelObserver()

        return view
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

    private fun viewModelObserver() {
        model!!.users.observe(this, Observer<List<User>> { taskSuccess ->
            progressDialog.setDialog(false, "") // hide dialog
            if (taskSuccess != null) { // it is empty
                arrayListOfPlans = taskSuccess as ArrayList<User>
                setAdaptorWithThisArrayList()

            } else {
                CustomDialogBuilder.dialogBuilder(activity!!.resources.getString(R.string.error), false, activity!!)

            }

        })
    }

    fun setAllViews(rootView: View) {
        noUsersTextView = rootView.findViewById<TextView>(R.id.no_user_text)
        recycleViewUsers =  rootView.findViewById<RecyclerView>(R.id.recycle_user)
      //  recycleViewUsers.isNestedScrollingEnabled = false // close it

    }


    override fun onAttach(context: Context?) {
        super.onAttach(context)


    }

    override fun onDetach() {
        super.onDetach()
    }
    fun noSolfFound(b: Boolean) {

            if (b)
                noUsersTextView.visibility = View.VISIBLE
            else
                noUsersTextView.visibility = View.GONE


    }


    fun setAdaptorWithThisArrayList() {
        if (arrayListOfPlans.isEmpty()){
            noSolfFound(true)
        } else
            noSolfFound(false)
        recycleAdaptor = RecycleAdaptorUsers(arrayListOfPlans)
        recycleViewUsers.layoutManager = LinearLayoutManager(activity, LinearLayout.VERTICAL, false)
        recycleViewUsers.adapter = recycleAdaptor
        recycleAdaptor.notifyDataSetChanged()
    }
}// Required empty public constructor
