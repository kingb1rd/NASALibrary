package com.infoechebo.nasalibrary.data.remote

import com.infoechebo.nasalibrary.data.remote.dto.NasaSearchResultDto
import retrofit2.http.GET
import retrofit2.http.Query

interface NasaMediaApi {
    @GET("/search?q=&page=1&media_type=image&year_start=1920&year_end=2022")
    suspend fun searchMedia(@Query("q") q: String): NasaSearchResultDto
}