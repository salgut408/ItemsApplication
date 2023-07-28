package com.salvador.myapplication.data.api

import com.salvador.myapplication.data.data_models.NetworkResponseModel
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("WN0G/")
    suspend fun getItemsList(): Response<NetworkResponseModel>
}