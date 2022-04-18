package com.xlwe.testpaginglibrary.network

import com.xlwe.testpaginglibrary.network.model.RickMortiDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiRequests {
    @GET("character")
    suspend fun getCharacters(
        @Query("page") page: Int
    ): Response<RickMortiDTO>
}