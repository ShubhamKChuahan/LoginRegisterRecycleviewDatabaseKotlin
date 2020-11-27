package com.shubham.beyondroot

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.collections.ArrayList

internal class ContactAdapter( context: Context, private var listContacts: ArrayList<Contacts>) : RecyclerView.Adapter<ContactViewHolder>() {
    private val mArrayList: ArrayList<Contacts>
    private val mDatabase: DatabaseHelper
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.contact_list_layout, parent, false)
        return ContactViewHolder(view)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val contacts = listContacts!![position]
        if (contacts != null) {
            holder.tvName.text = contacts.getName()
        }
        if (contacts != null) {
            holder.tvEmail.text = contacts.getEmail()
        }
        if (contacts != null) {
            holder.tvPhoneNum.text = contacts.getPhno()
        }
        if (contacts != null) {
            holder.tvGender.text = contacts.getGender()
        }
        if (contacts != null) {
            holder.tvCity.text = contacts.getCity()
        }
    }

    override fun getItemCount(): Int {
        return listContacts!!.size
    }

    init {
        mArrayList = listContacts
        mDatabase = DatabaseHelper(context)
    }
}