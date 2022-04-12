package com.exseed.health.data.remote

import com.exseed.health.data.constant.URLHelper
import com.exseed.health.data.response.RepositoriesListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface AppService {

    @GET(URLHelper.searchRepository)
    suspend fun getRepositoriesList(
        @Query("q") query: String,
        @Query("per_page") per_page: Int,
        @Query("page") page: Int
    ): Response<RepositoriesListResponse>

}