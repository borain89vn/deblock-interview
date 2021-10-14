package com.devblock.network.api.response

import com.google.gson.annotations.SerializedName

data class ContactResp (
    @SerializedName("data")
    var items : List<ContactItemResp>
)
