package com.devblock.network.api

import androidx.paging.PagingData
import com.devblock.network.api.response.ContactItemResp
import kotlinx.coroutines.flow.Flow



interface ContactApi {

    suspend fun getUsers(): Flow<PagingData<ContactItemResp>>
}