package com.exseed.health.ui.main_activity.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class OwnerDetails(
    @SerializedName("avatar_url")
    val avatar_url: String,
) : Parcelable
