package com.exseed.health.data.response

import com.exseed.health.ui.main_activity.model.Items
import com.google.gson.annotations.SerializedName

data class RepositoriesListResponse(
    @SerializedName("total_count")
    val total_count: Float,
    @SerializedName("incomplete_results")
    val incomplete_results: Boolean,
    @SerializedName("items")
    val items: List<Items>? = ArrayList()
)