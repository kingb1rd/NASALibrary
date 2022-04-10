package com.infoechebo.nasalibrary.data.repository

import com.infoechebo.nasalibrary.data.remote.NasaMediaApi
import com.infoechebo.nasalibrary.data.remote.dto.toDomain
import com.infoechebo.nasalibrary.domain.model.NasaImage
import com.infoechebo.nasalibrary.domain.repository.NasaImageRepository
import com.infoechebo.nasalibrary.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class NasaImageRepositoryImpl @Inject constructor(private val api: NasaMediaApi) :
    NasaImageRepository {

    override suspend fun searchMedia(query: String): Flow<Resource<List<NasaImage>>> = flow {
        emit(Resource.Loading())

        try {
            val result = api.searchMedia(query).toDomain()
            emit(Resource.Success(data = result))
        } catch (e: HttpException) {
            emit(
                Resource.Error(
                    message = "Oops, something went wrong",
                    data = null
                )
            )
        } catch (e: IOException) {
            emit(
                Resource.Error(
                    message = "Couldn't reach server, check your internet connection.",
                    data = null
                )
            )
        }
    }
}