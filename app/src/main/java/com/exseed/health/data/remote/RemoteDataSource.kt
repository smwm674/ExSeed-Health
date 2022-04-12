package com.exseed.health.data.remote

import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val appService: AppService
) : BaseDataSource() {

    suspend fun getRepositoriesList(query: String, per_page: Int, page: Int) =
        getResult { appService.getRepositoriesList(query, per_page, page) }
}