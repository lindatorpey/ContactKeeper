package org.wit.contactkeeper.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.github.ajalt.timberkt.Timber
import com.google.android.material.snackbar.Snackbar
import org.wit.contactkeeper.R
import org.wit.contactkeeper.databinding.ActivityContactkeeperBinding
import org.wit.contactkeeper.main.MainApp
import org.wit.contactkeeper.models.ContactkeeperModel
import timber.log.Timber.i

class ContactkeeperActivity : AppCompatActivity() {

    private lateinit var binding: ActivityContactkeeperBinding
    var contactkeeper = ContactkeeperModel()
    lateinit var app: MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
     var edit = false

        binding = ActivityContactkeeperBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbarAdd.title = title
        setSupportActionBar(binding.toolbarAdd)

        app = application as MainApp
        i("Contactkeeper Activity started")

        if (intent.hasExtra("contactkeeper_edit")){
            edit = true
            contactkeeper = intent.extras?.getParcelable("contactkeeper_edit")!!
            binding.contactkeeperTitle.setText(contactkeeper.title)
            binding.address.setText(contactkeeper.address)
            binding.number.setText(contactkeeper.number)
            binding.email.setText(contactkeeper.email)
            binding.btnAdd.setText(R.string.save_contactkeeper)
        }

        binding.btnAdd.setOnClickListener() {
            contactkeeper.title = binding.contactkeeperTitle.text.toString()
            contactkeeper.address = binding.address.text.toString()
            contactkeeper.number = binding.number.text.toString()
            contactkeeper.email = binding.email.text.toString()
            if (contactkeeper.title.isEmpty()) {
                Snackbar.make(it,R.string.enter_contactkeeper_title,Snackbar.LENGTH_LONG)
                    .show()
            } else {
                if (edit){
                    app.contactkeepers.update(contactkeeper.copy())
                } else {
                    app.contactkeepers.create(contactkeeper.copy())
                }
            }
            setResult(RESULT_OK)
            finish()
        }
        binding.chooseImage.setOnClickListener {
            i("Select Image")
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_contactkeeper, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.item_cancel -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}