package com.cst.contacts.adapter

import android.content.Intent
import android.graphics.Color
import android.graphics.LightingColorFilter
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import com.cst.contacts.R
import com.cst.contacts.details.ContactDetailedActivity
import com.cst.contacts.donottouch.ContactInfo
import kotlinx.android.synthetic.main.contact_item.view.*
import java.util.*


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
                val random = Random()
                val firstChar = name[0].toString()
                val iconBackground: Drawable? =
                    AppCompatResources.getDrawable(itemView.context, R.drawable.circle)
                val randomColor: Int =
                    Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256))
                iconBackground?.colorFilter = LightingColorFilter(randomColor, randomColor)

                when {
                    adapterPosition != 0 && contacts[adapterPosition - 1].name[0] == name[0] ->
                        itemView.first_char.text = ""
                    else -> itemView.first_char.text = firstChar
                }

                itemView.name.text = name
                itemView.image.text = firstChar
                itemView.image.background = iconBackground

                itemView.setOnClickListener() {
                    itemView.context.startActivity(Intent(itemView.context,
                        ContactDetailedActivity::class.java).apply {
                        putExtra("position", adapterPosition.toString())
                        putExtra("color", randomColor)
                    })
                }
            }
        }

    }
}