package com.devblock.welcome.model

import com.devblock.network.api.response.ContactItemResp
import com.devblock.utils.paging.ItemComparable
import com.devblock.utils.paging.ItemSeperator

sealed class ContactModel : ItemComparable {
    class Data
        (val avatar: String?, val fullName: String?, val email: String?) :
        ContactModel() {
        constructor(contact: ContactItemResp) : this(
            avatar = contact.avatar,
            fullName = contact.fullName(),
            email = contact.email

        )
    }

    class Seperator(val tag: String) : ContactModel(), ItemSeperator
}