package com.exseed.health.ui.main_activity.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Items(
    @SerializedName("full_name")
    val full_name: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("language")
    var language: String,
    @SerializedName("forks_count")
    val forks: Long,
    @SerializedName("open_issues_count")
    val open_issues: Long,
    @SerializedName("forks")
    val commits: Long,
    @SerializedName("updated_at")
    val lastRelase: String,
    @SerializedName("owner")
    val owner: OwnerDetails
) : Parcelable
