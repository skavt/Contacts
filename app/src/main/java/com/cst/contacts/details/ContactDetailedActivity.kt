package com.cst.contacts.details

import android.graphics.LightingColorFilter
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import com.cst.contacts.R
import com.cst.contacts.donottouch.ContactInfo
import com.cst.contacts.donottouch.mapToContactInfo
import com.github.tamir7.contacts.Contact
import com.github.tamir7.contacts.Contacts
import kotlinx.android.synthetic.main.contact_detailed.*

/**
 * ამ ექთივითის უნდა გადმოაწოდოთ კონტაქტის ID,
 * რომელიც თავის მხრივ გადააწვდის ამ ID-ს ფრაგმენტს
 */
class ContactDetailedActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.contact_detailed)

        // Toolbar back arrow
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        toolbar.title = ""
        setSupportActionBar(toolbar)

        val contactData = getContactById(intent.getStringExtra("contact_id").toString())
        val color = intent.getStringExtra("color").toString().toInt()
        val iconBackground: Drawable? =
            AppCompatResources.getDrawable(this, R.drawable.circle)
        iconBackground?.colorFilter = LightingColorFilter(color, color)

        if (contactData != null) {
            contact_name.text = contactData.name
            profile_image.text = contactData.name[0].toString()
        }
        profile_image.background = iconBackground

    }

    private fun getContactById(id: String): ContactInfo? {
        return Contacts.getQuery().whereEqualTo(Contact.Field.ContactId, id)
            .find().firstOrNull()?.mapToContactInfo()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }
}