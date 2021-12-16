package org.wit.contactkeeper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.ajalt.timberkt.Timber
import com.google.android.material.snackbar.Snackbar
import org.wit.contactkeeper.databinding.ActivityContactkeeperBinding
import org.wit.contactkeeper.models.ContactkeeperModel
import timber.log.Timber.i

class ContactkeeperActivity : AppCompatActivity() {

    private lateinit var binding: ActivityContactkeeperBinding
    var contactkeeper = ContactkeeperModel()
    val contactkeepers = ArrayList<ContactkeeperModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contactkeeper)

        binding = ActivityContactkeeperBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Timber.plant(Timber.DebugTree())
        i("Contactkeeper Activity started")



        binding.btnAdd.setOnClickListener() {
            contactkeeper.title = binding.contactkeeperTitle.text.toString()
            contactkeeper.address = binding.address.text.toString()
            contactkeeper.number = binding.number.text.toString()
            contactkeeper.email = binding.email.text.toString()
            if (contactkeeper.title.isNotEmpty()) {
                contactkeepers.add(contactkeeper.copy())
                i("add Button Pressed: ${contactkeeper}")
                for (i in contactkeepers.indices)
                {i("Contactkeeper[$i]:${this.contactkeepers[i]}")}
            }
            else {
                Snackbar
                    .make(it,"Please Enter a Name", Snackbar.LENGTH_LONG)
                    .show()
            }
        }
    }
}