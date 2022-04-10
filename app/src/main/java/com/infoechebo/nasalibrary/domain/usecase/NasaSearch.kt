package com.infoechebo.nasalibrary.domain.usecase

import com.infoechebo.nasalibrary.domain.model.NasaImage
import com.infoechebo.nasalibrary.domain.repository.NasaImageRepository
import com.infoechebo.nasalibrary.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NasaSearch @Inject constructor(private val repository: NasaImageRepository) {
    suspend operator fun invoke(query: String): Flow<Resource<List<NasaImage>>> {
        return if (query.isBlank())
            flow { }
        else
            repository.searchMedia(query)
    }
}