package com.devblock.network.api

import com.devblock.network.api.response.ContactItemResp
import com.devblock.network.api.response.ContactResp


interface ContactApi {

    suspend fun getUsers(): ContactResp
}