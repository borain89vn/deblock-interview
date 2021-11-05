package com.devblock.welcome


import com.devblock.adapter.SingleLayoutPagingAdapter

import com.devblock.welcome.databinding.ItemContactBinding
import com.devblock.welcome.model.ContactModel

class WelcomePagingAdapter : SingleLayoutPagingAdapter<ContactModel,ItemContactBinding>(R.layout.item_contact) {


}