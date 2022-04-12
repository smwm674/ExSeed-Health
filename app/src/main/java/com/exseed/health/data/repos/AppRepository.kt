package com.exseed.health.data.repos

import com.exseed.health.data.remote.RemoteDataSource
import com.exseed.health.utils.NetworkOnly
import javax.inject.Inject

class AppRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) {
    fun getRepositoriesList(query: String, per_page: Int, page: Int) = NetworkOnly(
        networkCall = { remoteDataSource.getRepositoriesList(query, per_page, page) }
    )
}