package com.infoechebo.nasalibrary.domain.model

import java.io.Serializable

data class NasaImage(
    val nasa_id: String?,
    val title: String?,
    val description: String?,
    val href: String?,
    val photographer: String?,
    val location: String?
) : Serializable