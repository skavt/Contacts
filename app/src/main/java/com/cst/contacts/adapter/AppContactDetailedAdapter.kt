package com.cst.contacts.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cst.contacts.R
import com.cst.contacts.donottouch.Email
import com.cst.contacts.donottouch.PhoneNumber
import kotlinx.android.synthetic.main.contact_detailed_item.view.*
import java.util.*
import kotlin.collections.ArrayList

class AppContactDetailedAdapter(
    private val numberList: ArrayList<PhoneNumber>,
    private val emailList: ArrayList<Email>
) :
    RecyclerView.Adapter<AppContactDetailedAdapter.ViewHolder>() {

    override fun getItemCount(): Int = numberList.size + emailList.size

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AppContactDetailedAdapter.ViewHolder = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.contact_detailed_item, parent, false)
    )

    override fun onBindViewHolder(holder: AppContactDetailedAdapter.ViewHolder, position: Int) {
        holder.setContent()
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private lateinit var phoneNumber: PhoneNumber
        private lateinit var email: Email

        fun setContent() {
            if (adapterPosition < numberList.size) {
                phoneNumber = numberList[adapterPosition]

                itemView.main_text.text = phoneNumber.number
                itemView.type.text = capitalize(phoneNumber.type.toString())
            } else {
                email = emailList[adapterPosition - numberList.size]

                itemView.main_text.text = email.address
                itemView.type.text = capitalize(email.type.toString())
            }
        }

        private fun capitalize(string: String): String {
            return string
                .substring(0, 1)
                .toUpperCase(Locale.ROOT) + string
                .substring(1)
                .toLowerCase(Locale.ROOT)
        }

    }
}