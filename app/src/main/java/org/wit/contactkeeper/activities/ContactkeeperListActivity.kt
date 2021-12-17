package org.wit.contactkeeper.activities


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
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
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_add -> {
                val launcherIntent = Intent(this, ContactkeeperActivity::class.java)
                startActivityForResult(launcherIntent,0)
            }
        }
        return super.onOptionsItemSelected(item)
    }
    override fun onContactkeeperClick(contactkeeper: ContactkeeperModel) {
        val launcherIntent = Intent(this, ContactkeeperActivity::class.java)
        launcherIntent.putExtra("contactkeeper_edit", contactkeeper)
        startActivityForResult(launcherIntent,0)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        binding.recyclerView.adapter?.notifyDataSetChanged()
        super.onActivityResult(requestCode, resultCode, data)
    }

}

