package com.pdm.sweatworkstest.framework.network

import com.pdm.sweatworkstest.framework.network.responses.ApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET(".")
    suspend fun getNews(@Query("page") page: Int, @Query("results") results: Int = 50): Response<ApiResponse>
}