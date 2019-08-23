package com.project.imperialcreation.userinfo.LoadingUsers
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.user_info_layout.view.*
import android.view.animation.AnimationUtils
import com.project.imperialcreation.userinfo.Model.User
import com.project.imperialcreation.userinfo.R


class RecycleAdaptorUsers(val arrayListUsersValue:ArrayList<User>//this method is returning the view for each item in the list
) : RecyclerView.Adapter<RecycleAdaptorUsers.ViewHolder>()  {
    var lastPosition = -1
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecycleAdaptorUsers.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.fragement_user_item_view_items, parent, false)
        return ViewHolder(v)

    }

    //this method is binding the data on the list
    override fun onBindViewHolder(holder: RecycleAdaptorUsers.ViewHolder, position: Int) {
        holder.bindItems(arrayListUsersValue[position])
        setAnimation(holder.itemView, position)


    }

    private fun setAnimation(viewToAnimate: View, position: Int) {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition) {
            val animation = AnimationUtils.loadAnimation(viewToAnimate.context, android.R.anim.slide_in_left)
            animation.duration =800
            viewToAnimate.startAnimation(animation)
            lastPosition = position
        }
    }

    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return arrayListUsersValue.size
    }



    //the class is hodling the list view
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        fun bindItems(user : User) {

            itemView.user_name.text = user.userName
            itemView.kind_text.text = user.userKind
            itemView.job_title_text.text = user.jobTitle
            itemView.age_textview.text = user.age.toString()



        }


    }

}