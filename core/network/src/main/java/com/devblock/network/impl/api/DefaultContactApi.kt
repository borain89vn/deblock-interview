package com.devblock.network.impl.api


import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.devblock.network.api.ContactApi
import com.devblock.network.api.response.ContactItemResp
import com.devblock.network.impl.ApiService
import com.devblock.network.paging.datasource.ContactPagingSource
import kotlinx.coroutines.flow.Flow

import javax.inject.Inject

internal class DefaultContactApi @Inject constructor(
    private val apiService: ApiService
) : ContactApi {


    override suspend fun getUsers(): Flow<PagingData<ContactItemResp>> = Pager(
        config = PagingConfig(pageSize = 20, prefetchDistance = 2),
        pagingSourceFactory = { ContactPagingSource(apiService) }
    ).flow
}