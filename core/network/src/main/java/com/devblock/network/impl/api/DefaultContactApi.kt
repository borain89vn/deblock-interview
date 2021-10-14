package com.devblock.network.impl.api


import com.devblock.network.api.ContactApi
import com.devblock.network.api.response.ContactItemResp
import com.devblock.network.api.response.ContactResp
import com.devblock.network.impl.ApiService

import javax.inject.Inject

internal class DefaultContactApi @Inject constructor(
    private val apiService: ApiService
) : ContactApi {

    override suspend fun getUsers(): ContactResp {
       return apiService.getContacts()
    }
}