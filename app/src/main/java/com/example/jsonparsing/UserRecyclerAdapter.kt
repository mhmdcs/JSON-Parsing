package com.example.jsonparsing

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_user.view.*

class UserRecyclerAdapter(private val context: Context,private val items: ArrayList<User>): RecyclerView.Adapter<UserRecyclerAdapter.ViewHolder>(){

    /**
     * A ViewHolder describes an item view and metadata about its place within the RecyclerView.
     */
    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        // Holds the TextViews that we will add each item to
        val tvId = view.tv_id
        val tvName = view.tv_name
        val tvEmail = view.tv_email
        val tvGender = view.tv_gender
        val tvWeight = view.tv_weight
        val tvHeight = view.tv_height
        val tvMobileNumber = view.tv_mobile
        val tvOfficeNumber = view.tv_office_number
        val tvPhoneBrand = view.tv_phone_brand
        val tvPhoneModel = view.tv_phone_model
    }

    /**
     * Inflates the item views which is designed in xml layout file
     * create a new ViewHolder and initializes some private fields to be used by RecyclerView.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_user,
                parent,
                false
            )
        )    }

    /**
     * Binds each item in the ArrayList to a view
     * Called when RecyclerView needs a new ViewHolder of the given type to represent an item.
     * This new ViewHolder should be constructed with a new View that can represent the items of the given type.
     * You can either create a new View manually or inflate it from an XML layout file.
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items.get(position)

        holder.tvId.text = item.id.toString()
        holder.tvName.text = item.name
        holder.tvEmail.text = item.email
        holder.tvGender.text = item.gender
        holder.tvWeight.text = item.weight.toString()
        holder.tvHeight.text = item.height.toString()
        holder.tvMobileNumber.text = item.phone.mobile
        holder.tvOfficeNumber.text = item.phone.office
        holder.tvPhoneBrand.text = item.phone.phoneType.brand
        holder.tvPhoneModel.text = item.phone.phoneType.model

    }

    /**
     * Gets the number of items in the list
     */
    override fun getItemCount(): Int {
        return items.size
    }
}