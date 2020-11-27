package com.shubham.beyondroot

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

internal class ContactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var tvName: TextView
    var tvEmail: TextView
    var tvPhoneNum: TextView
    var tvGender: TextView
    var tvCity: TextView

    init {
        tvName = itemView.findViewById(R.id.cName)
        tvEmail = itemView.findViewById(R.id.cEmail)
        tvPhoneNum = itemView.findViewById(R.id.cphoneNum)
        tvGender = itemView.findViewById(R.id.cGender)
        tvCity = itemView.findViewById(R.id.cCity)
    }
}