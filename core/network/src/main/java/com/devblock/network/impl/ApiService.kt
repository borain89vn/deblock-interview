package com.devblock.network.impl

import com.devblock.network.api.response.*
import retrofit2.Response

import retrofit2.http.*

internal interface ApiService {


    @GET("users")
    suspend fun getContacts(@QueryMap hashMap: HashMap<String, String> = HashMap()): Response<PagedResponse<ContactItemResp>>

}