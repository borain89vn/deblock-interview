package com.devblock.network.paging.datasource

import com.devblock.network.api.ApiParams
import com.devblock.network.api.ContactApi
import com.devblock.network.api.response.ContactItemResp
import com.devblock.network.api.response.PagedResponse
import com.devblock.network.impl.ApiService
import retrofit2.Response

internal class ContactPagingSource(
    private val apiService: ApiService
    ) : BasePagingSource<ContactItemResp>() {

    override suspend fun getApiService(hashMap: HashMap<String, String>): Response<PagedResponse<ContactItemResp>> {
        return apiService.getContacts(hashMap)
    }

    override fun getParams(params: LoadParams<Int>): HashMap<String, String> {
        val hashMap = HashMap<String, String>()
        hashMap[ApiParams.PAGE] = (params.key ?: getFirstPage()).toString()
        return hashMap
    }
}
