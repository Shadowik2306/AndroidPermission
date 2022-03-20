package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ContactsAdapter(private val contacts: List<String>) : RecyclerView.Adapter<ContactsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactsViewHolder {
        val rootView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.recyclerview_row, parent, false)
        return ContactsViewHolder(rootView)
    }

    override fun onBindViewHolder(holder: ContactsViewHolder, position: Int) {
        holder.bind(contacts[position])
    }

    override fun getItemCount(): Int {
        return contacts.size
    }
}

class ContactsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val contactName: TextView = itemView.findViewById(R.id.textViewLarge)

    fun bind(contact: String) {
        contactName.text = contact
    }
}