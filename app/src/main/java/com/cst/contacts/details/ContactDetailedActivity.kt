package com.cst.contacts.details

import android.annotation.SuppressLint
import android.graphics.LightingColorFilter
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.graphics.drawable.DrawableCompat
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

    @SuppressLint("ResourceAsColor")
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

        val contactData = getContactById(intent.getStringExtra("contact_id").toString())
        val color = intent.getStringExtra("color").toString().toInt()
        val iconBackground: Drawable? =
            AppCompatResources.getDrawable(this, R.drawable.circle)
        iconBackground?.colorFilter = LightingColorFilter(color, color)

        profile_image.background = iconBackground

        if (contactData != null) {
            contact_name.text = contactData.name
            profile_image.text = contactData.name[0].toString()

            if (contactData.phoneNumbers.isNotEmpty()) {
                phone_icon.isEnabled = true
                message_icon.isEnabled = true
                video_icon.isEnabled = true

                phone_icon.setOnClickListener {
                    Toast.makeText(this, phone_icon.text, Toast.LENGTH_SHORT).show()
                }
                message_icon.setOnClickListener {
                    Toast.makeText(this, message_icon.text, Toast.LENGTH_SHORT).show()
                }
                video_icon.setOnClickListener {
                    Toast.makeText(this, video_icon.text, Toast.LENGTH_SHORT).show()
                }
            }
            if (contactData.emails.isNotEmpty()) {
                email_icon.isEnabled = true

                email_icon.setOnClickListener {
                    Toast.makeText(this, email_icon.text, Toast.LENGTH_SHORT).show()
                }
                /*val unwrappedDrawable = AppCompatResources.getDrawable(this, R.drawable.bg_email)
                val wrappedDrawable = DrawableCompat.wrap(unwrappedDrawable!!)
                DrawableCompat.setTint(wrappedDrawable, R.color.darkGrey)*/
            }
        }

        detailed_info.layoutManager = LinearLayoutManager(this)
        detailed_info.adapter =
            AppContactDetailedAdapter(contactData?.phoneNumbers as ArrayList<PhoneNumber>,
                contactData.emails as ArrayList<Email>)
    }

    private fun getContactById(id: String): ContactInfo? {
        return Contacts.getQuery().whereEqualTo(Contact.Field.ContactId, id)
            .find().firstOrNull()?.mapToContactInfo()
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