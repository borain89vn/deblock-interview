package com.devblock.contact

import com.devblock.base.UIState
import com.devblock.utils.paging.ItemComparable


sealed class ContactState: UIState {
    data class Contact(val name: String, val email: String, val avatar: String): ContactState()
    data class UpdateSuccess(val message: String): ContactState()
    data class UpdateFail(val message: String?): ContactState()

}