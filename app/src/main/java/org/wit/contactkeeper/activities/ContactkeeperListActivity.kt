package org.wit.contactkeeper.activities


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import org.wit.contactkeeper.R
import org.wit.contactkeeper.adapters.ContactkeeperAdapter
import org.wit.contactkeeper.adapters.ContactkeeperListener
import org.wit.contactkeeper.databinding.ActivityContactkeeperListBinding
import org.wit.contactkeeper.main.MainApp
import org.wit.contactkeeper.models.ContactkeeperModel


class ContactkeeperListActivity : AppCompatActivity(), ContactkeeperListener {

    lateinit var app: MainApp
    private lateinit var binding: ActivityContactkeeperListBinding
    private lateinit var refreshIntentLauncher : ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactkeeperListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbar.title = title
        setSupportActionBar(binding.toolbar)

        app = application as MainApp

        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = ContactkeeperAdapter(app.contactkeepers.findAll(),this)

        registerRefreshCallback()
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_add -> {
                val launcherIntent = Intent(this, ContactkeeperActivity::class.java)
                refreshIntentLauncher.launch(launcherIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }
    override fun onContactkeeperClick(contactkeeper: ContactkeeperModel) {
        val launcherIntent = Intent(this, ContactkeeperActivity::class.java)
        launcherIntent.putExtra("contactkeeper_edit", contactkeeper)
        refreshIntentLauncher.launch(launcherIntent)

    }
    private fun registerRefreshCallback() {
        refreshIntentLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult())
            { binding.recyclerView.adapter?.notifyDataSetChanged() }
    }

}

