package org.wit.contactkeeper.activities

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import com.github.ajalt.timberkt.Timber
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import org.wit.contactkeeper.R
import org.wit.contactkeeper.databinding.ActivityContactkeeperBinding
import org.wit.contactkeeper.helpers.showImagePicker
import org.wit.contactkeeper.main.MainApp
import org.wit.contactkeeper.models.ContactkeeperModel
import timber.log.Timber.i

class ContactkeeperActivity : AppCompatActivity() {

    private lateinit var binding: ActivityContactkeeperBinding
    var contactkeeper = ContactkeeperModel()
    lateinit var app: MainApp
    private lateinit var imageIntentLauncher : ActivityResultLauncher<Intent>

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
            Picasso.get()
                .load(contactkeeper.image)
                .into(binding.contactkeeperImage)
            if (contactkeeper.image != Uri.EMPTY){
                binding.chooseImage.setText(R.string.change_contactkeeper_image)
            }
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
            showImagePicker(imageIntentLauncher)
        }
        registerImagePickerCallback()
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

    private fun registerImagePickerCallback() {
        imageIntentLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult())
            { result ->
                when(result.resultCode){
                    RESULT_OK -> {
                        if (result.data != null) {
                            i("Got Result ${result.data!!.data}")
                            contactkeeper.image = result.data!!.data!!
                            Picasso.get()
                                .load(contactkeeper.image)
                                .into(binding.contactkeeperImage)
                            binding.chooseImage.setText(R.string.change_contactkeeper_image)
                        } // end of if
                    }
                    RESULT_CANCELED -> { } else -> { }
                }
            }
    }

}