package com.example.myapplication

import android.app.Activity
import android.app.AlertDialog
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.ContactsContract
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

const val MY_PERMISSION_REQUEST_READ_CONTACTS = 0

class FirstFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (ActivityCompat.checkSelfPermission(view.context, android.Manifest.permission.READ_CONTACTS)
            == PackageManager.PERMISSION_GRANTED) setContacts()
        view.findViewById<View>(R.id.button).setOnClickListener() {
            if (ActivityCompat.checkSelfPermission(view.context, android.Manifest.permission.READ_CONTACTS)
                == PackageManager.PERMISSION_DENIED) { requestContactPermission() }
            else setContacts()
        }
    }

    private fun requestContactPermission() {
        if (shouldShowRequestPermissionRationale(android.Manifest.permission.READ_CONTACTS)) {
            requestPermissions(arrayOf(android.Manifest.permission.READ_CONTACTS), MY_PERMISSION_REQUEST_READ_CONTACTS)
        } else {
            requestPermissions(arrayOf(android.Manifest.permission.READ_CONTACTS), MY_PERMISSION_REQUEST_READ_CONTACTS)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == MY_PERMISSION_REQUEST_READ_CONTACTS) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                setContacts()
            }
        }
    }

    private fun setContacts() {
        val contacts = mutableListOf<String>("Mark", "Alex")
/*        val cursor = context?.contentResolver?.query(ContactsContract.Contacts.CONTENT_GROUP_URI,
            null, null, null, null)
        cursor?.let { _ ->
            while (cursor.moveToNext()) {
                val index = cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME_PRIMARY)
                val contact: String
                if (index >= 0) {
                    contact = cursor.getString(index)
                    contacts.add(contact)
                }
            }
            cursor.close()
        }*/

        val contactsList: RecyclerView? = view?.findViewById(R.id.recycle)
        if (contactsList != null) {
            val adapter = ContactsAdapter(contacts)
            contactsList.adapter = adapter

            val layoutManager = LinearLayoutManager(context)
            contactsList.layoutManager = layoutManager

        }

    }

}