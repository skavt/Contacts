package com.cst.contacts.details

import android.annotation.SuppressLint
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.cst.contacts.R
import com.cst.contacts.adapter.AppContactDetailedAdapter
import com.cst.contacts.donottouch.ContactInfo
import com.cst.contacts.donottouch.Email
import com.cst.contacts.donottouch.PhoneNumber
import com.cst.contacts.donottouch.mapToContactInfo
import com.github.tamir7.contacts.Contact
import com.github.tamir7.contacts.Contacts
import kotlinx.android.synthetic.main.contact_detailed.*


/**
 * ამ ექთივითის უნდა გადმოაწოდოთ კონტაქტის ID,
 * რომელიც თავის მხრივ გადააწვდის ამ ID-ს ფრაგმენტს
 */
class ContactDetailedActivity : AppCompatActivity() {

    private val colorBlue = 0xFF1A73E8.toInt()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.contact_detailed)

        // Toolbar back arrow
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        toolbar.title = ""
        setSupportActionBar(toolbar)

        phone_icon.isEnabled = false
        message_icon.isEnabled = false
        video_icon.isEnabled = false
        email_icon.isEnabled = false

        val contactData =
            ContactDetailedFragment()
                .getContactById(intent.getStringExtra("contact_id").toString().toLong())
        val color = intent.getStringExtra("color").toString().toInt()

        val iconBackground = ContextCompat.getDrawable(this, R.drawable.circle)
        iconBackground!!.colorFilter = PorterDuffColorFilter(color, PorterDuff.Mode.SRC_IN)

        profile_image.background = iconBackground

        if (contactData != null) {
            contact_name.text = contactData.name
            profile_image.text = contactData.name[0].toString()

            if (contactData.phoneNumbers.isNotEmpty()) {
                phone_icon.isEnabled = true
                message_icon.isEnabled = true
                video_icon.isEnabled = true

                phone_icon.setOnClickListener {
                    Toast.makeText(this, phone_text.text, Toast.LENGTH_SHORT).show()
                }
                phone_icon.background = setIconColor(R.drawable.ic_phone, colorBlue)
                phone_text.setTextColor(ContextCompat.getColor(this, R.color.blue));

                message_icon.setOnClickListener {
                    Toast.makeText(this, message_text.text, Toast.LENGTH_SHORT).show()
                }
                message_icon.background = setIconColor(R.drawable.ic_message, colorBlue)
                message_text.setTextColor(ContextCompat.getColor(this, R.color.blue));

                video_icon.setOnClickListener {
                    Toast.makeText(this, video_text.text, Toast.LENGTH_SHORT).show()
                }
                video_icon.background = setIconColor(R.drawable.ic_video, colorBlue)
                video_text.setTextColor(ContextCompat.getColor(this, R.color.blue));
            }
            if (contactData.emails.isNotEmpty()) {
                email_icon.isEnabled = true

                email_icon.setOnClickListener {
                    Toast.makeText(this, email_text.text, Toast.LENGTH_SHORT).show()
                }
                email_icon.background = setIconColor(R.drawable.ic_email, colorBlue)
                email_text.setTextColor(ContextCompat.getColor(this, R.color.blue));
            }
        }

        detailed_info.layoutManager = LinearLayoutManager(this)
        detailed_info.adapter =
            AppContactDetailedAdapter(contactData?.phoneNumbers as ArrayList<PhoneNumber>,
                contactData.emails as ArrayList<Email>)
    }

    private fun setIconColor(drawable: Int, color: Int): Drawable? {
        val iconBackground = ContextCompat.getDrawable(this, drawable)
        iconBackground!!.colorFilter = PorterDuffColorFilter(color, PorterDuff.Mode.MULTIPLY)
        return iconBackground
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.star -> Toast.makeText(this, R.string.star, Toast.LENGTH_SHORT).show()
            R.id.dots -> Toast.makeText(this, R.string.three_dots, Toast.LENGTH_SHORT).show()
            else -> return super.onOptionsItemSelected(item)
        }
        return true
    }

}