package com.devblock.navigation.api.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

sealed class Destination : Parcelable {

    @Parcelize
    data class Contact(
        val id: String,
        val name: String?,
        val email: String?,
        val avatar: String?,
    ) : Destination()

    @Parcelize
    data class Login(val targetDestination: Destination? = null) : Destination()

    @Parcelize
    object LoginTarget : Destination()

    @Parcelize
    object Back : Destination()

    @Parcelize
    object Welcome : Destination()

}

