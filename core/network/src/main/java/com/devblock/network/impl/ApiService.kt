package com.devblock.network.impl

import com.devblock.network.api.response.*

import retrofit2.http.*

internal interface ApiService {


    @GET("users")
    suspend fun getContacts(
    ): ContactResp

}