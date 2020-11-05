package com.cst.contacts.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cst.contacts.R
import com.cst.contacts.donottouch.ContactInfo
import kotlinx.android.synthetic.main.contact_item.view.*

class AppContactsAdapter(
    private val contacts: List<ContactInfo>
) :
    RecyclerView.Adapter<AppContactsAdapter.ViewHolder>() {

    override fun getItemCount(): Int = contacts.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.contact_item, parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setContent((contacts[position]))
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun setContent(contactInfo: ContactInfo) {
            with(contactInfo) {
                when {
                    adapterPosition != 0 && contacts[adapterPosition - 1].name[0] == name[0] ->
                        itemView.first_char.text = ""
                    else -> itemView.first_char.text = name[0].toString()
                }
                itemView.name.text = name
            }
        }
    }
}