package org.wit.contactkeeper.models

import android.net.Uri
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ContactkeeperModel(var id: Long = 0,
                              var title: String = "",
                                var address: String = "",
                                var number: String = "",
                                var email: String = "",
                                var image: Uri= Uri.EMPTY) : Parcelable
@Parcelize
data class Location(var lat: Double = 0.0,
                    var lng: Double = 0.0,
                    var zoom: Float = 0f) : Parcelable
