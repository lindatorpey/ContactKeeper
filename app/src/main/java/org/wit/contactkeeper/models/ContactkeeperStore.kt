package org.wit.contactkeeper.models

interface ContactkeeperStore {
    fun findAll(): List<ContactkeeperModel>
    fun create(contactkeeper:ContactkeeperModel)
    fun update(contactkeeper: ContactkeeperModel)
}