package com.cst.contacts.adapter

import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.cst.contacts.R
import com.cst.contacts.donottouch.Email
import com.cst.contacts.donottouch.PhoneNumber
import kotlinx.android.synthetic.main.contact_detailed_item.view.*
import java.util.*

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
        private val colorBlue = 0xFF1A73E8.toInt()

        fun setContent() {
            val iconBackground = ContextCompat.getDrawable(itemView.context, R.drawable.ic_message)
            iconBackground!!.colorFilter =
                PorterDuffColorFilter(colorBlue, PorterDuff.Mode.MULTIPLY)

            if (adapterPosition < numberList.size) {
                phoneNumber = numberList[adapterPosition]

                itemView.main_text.text = phoneNumber.number
                itemView.type.text = capitalize(phoneNumber.type.toString())

                itemView.right_icon.apply {
                    setOnClickListener {
                        Toast.makeText(itemView.context, "Message", Toast.LENGTH_SHORT).show()
                    }
                    background = iconBackground
                }

                when {
                    adapterPosition == numberList.size - 1 && emailList.size == 0 -> {
                        itemView.last_line.isVisible = true
                    }
                }

                when (adapterPosition) {
                    0 -> itemView.left_icon.apply {
                        setOnClickListener {
                            Toast.makeText(context, "Call", Toast.LENGTH_SHORT).show()
                        }

                        background = ContextCompat.getDrawable(context, R.drawable.ic_phone)
                    }
                }
            } else {
                email = emailList[adapterPosition - numberList.size]

                itemView.main_text.text = email.address
                itemView.type.text = capitalize(email.type.toString())

                when {
                    adapterPosition - numberList.size == emailList.size - 1 -> {
                        itemView.last_line.isVisible = true
                    }
                }

                when (adapterPosition) {
                    numberList.size -> {
                        itemView.left_icon.apply {
                            setOnClickListener {
                                Toast.makeText(context, "Email", Toast.LENGTH_SHORT).show()
                            }
                            background =
                                ContextCompat.getDrawable(itemView.context, R.drawable.ic_email)
                        }
                        when {
                            numberList.size != 0 -> itemView.short_line.isVisible = true
                        }
                    }
                }
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