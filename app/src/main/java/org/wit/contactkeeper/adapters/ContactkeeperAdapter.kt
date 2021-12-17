package org.wit.contactkeeper.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.wit.contactkeeper.databinding.CardContactkeeperBinding
import org.wit.contactkeeper.models.ContactkeeperModel

interface ContactkeeperListener {
    fun onContactkeeperClick(contactkeeper: ContactkeeperModel)
}
class ContactkeeperAdapter constructor(private var contactkeepers: List<ContactkeeperModel>,
                            private val listener: ContactkeeperListener) :
                             RecyclerView.Adapter<ContactkeeperAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val binding = CardContactkeeperBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return MainHolder(binding)
    }
    override fun onBindViewHolder(holder: MainHolder,position: Int){
    val contactkeeper = contactkeepers[holder.adapterPosition]
    holder.bind(contactkeeper, listener)
}

    override fun getItemCount(): Int = contactkeepers.size

    class MainHolder(private val binding : CardContactkeeperBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(contactkeeper: ContactkeeperModel, listener: ContactkeeperListener) {
            binding.contactkeeperTitle.text = contactkeeper.title
            binding.description.text = contactkeeper.address
            binding.root.setOnClickListener { listener.onContactkeeperClick(contactkeeper) }
        }
    }
}