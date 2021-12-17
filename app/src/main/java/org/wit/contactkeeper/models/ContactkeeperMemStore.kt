package org.wit.contactkeeper.models

import timber.log.Timber.i

var lastId = 0L

internal fun getId(): Long{
    return lastId++
}

class ContactkeeperMemStore : ContactkeeperStore {

    val contactkeepers = ArrayList<ContactkeeperModel>()

    override fun findAll(): List<ContactkeeperModel> {
        return contactkeepers
    }

    override fun create(contactkeeper: ContactkeeperModel) {
        contactkeeper.id = getId()
        contactkeepers.add(contactkeeper)
        logAll()
    }

    override fun update(contactkeeper: ContactkeeperModel){
        var foundContactkeeper: ContactkeeperModel? = contactkeepers.find { p -> p.id == contactkeeper.id }
        if (foundContactkeeper !=null){
            foundContactkeeper.title = contactkeeper.title
            foundContactkeeper.address = contactkeeper.address
            foundContactkeeper.number = contactkeeper.number
            foundContactkeeper.email = contactkeeper.email
            logAll()
        }
    }
    fun logAll(){
        contactkeepers.forEach{i("${it}")}
    }
}