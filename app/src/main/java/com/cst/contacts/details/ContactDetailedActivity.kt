package com.cst.contacts.details

import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import com.cst.contacts.R
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

        Log.d("lala", intent.getStringExtra("position").toString())
        Log.d("ooo", intent.getStringExtra("color").toString())

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }
}