package org.wit.contactkeeper.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ContactkeeperModel(var id: Long = 0,
                              var title: String = "",
                                var address: String = "",
                                var number: String = "",
                                var email: String = "") : Parcelable
