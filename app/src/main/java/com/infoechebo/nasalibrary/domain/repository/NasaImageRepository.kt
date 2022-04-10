package com.infoechebo.nasalibrary.domain.repository

import com.infoechebo.nasalibrary.domain.model.NasaImage
import com.infoechebo.nasalibrary.util.Resource
import kotlinx.coroutines.flow.Flow

interface NasaImageRepository {
    suspend fun searchMedia(query: String): Flow<Resource<List<NasaImage>>>
}