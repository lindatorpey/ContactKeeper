package org.wit.contactkeeper.main
import android.app.Application
import org.wit.contactkeeper.models.ContactkeeperMemStore
import org.wit.contactkeeper.models.ContactkeeperModel
import timber.log.Timber
import timber.log.Timber.i

class MainApp: Application() {
    //val contactkeepers = ArrayList<ContactkeeperModel>()
    val contactkeepers = ContactkeeperMemStore()
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        i("Contactkeeper started")
        //contactkeepers.add(ContactkeeperModel("one","About one..."))
        //contactkeepers.add(ContactkeeperModel("two","About two..."))
    }

}