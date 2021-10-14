package com.devblock.network.api.response



data class ContactItemResp(
    val id: String,
    var email: String?,
    val first_name: String?,
    val last_name: String?,
    val avatar: String?,
    var full_name:String?
) {
    fun fullName():String = full_name?:first_name + last_name
}