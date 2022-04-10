package com.infoechebo.nasalibrary.data.remote.dto

import com.infoechebo.nasalibrary.domain.model.NasaImage

data class NasaSearchResultDto(
    val collection: Collection
)

fun NasaSearchResultDto.toDomain(): List<NasaImage> {
    return collection.items.map {
        NasaImage(
            it.data[0].nasaId,
            it.data[0].title,
            it.data[0].description,
            it.links[0].href,
            it.data[0].photographer,
            it.data[0].center
        )
    }
}