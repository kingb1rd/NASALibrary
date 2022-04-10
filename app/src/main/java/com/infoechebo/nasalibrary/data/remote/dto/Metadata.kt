package com.infoechebo.nasalibrary.data.remote.dto

import com.google.gson.annotations.SerializedName

data class Metadata(
    @SerializedName("total_hits")
    val totalHits: Int
)