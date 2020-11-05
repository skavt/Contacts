package com.cst.contacts.contacts

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.cst.contacts.R
import com.cst.contacts.adapter.AppContactsAdapter
import com.cst.contacts.donottouch.ContactInfo
import com.cst.contacts.donottouch.mapToContactInfo
import com.github.tamir7.contacts.Contacts
import kotlinx.android.synthetic.main.fragment_contacts.*

class ContactsFragment : Fragment(R.layout.fragment_contacts) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkPermissionsAndGetContacts()
    }

    private fun displayContacts(contacts: List<ContactInfo>) {
        contact_item.layoutManager = LinearLayoutManager(context)
        contact_item.adapter = AppContactsAdapter(contacts)

        search.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                Log.d("as", p0.toString())
            }

        })
    }

    /** ======== ამის ქვევით კოდს არ შეეხოთ ============= **/

    private fun checkPermissionsAndGetContacts() {
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.READ_CONTACTS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissions(
                arrayOf(Manifest.permission.READ_CONTACTS),
                123
            )
        } else {
            displayContacts(getContacts())
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == 123 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            displayContacts(getContacts())
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    private fun getContacts() =
        Contacts.getQuery().find().map {
            it.mapToContactInfo()
        }
}