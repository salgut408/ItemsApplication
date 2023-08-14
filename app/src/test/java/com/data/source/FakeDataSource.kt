package com.data.source

import com.salvador.myapplication.data.api.ApiService
import com.salvador.myapplication.data.data_models.NetworkResponseModel
import retrofit2.Response

class FakeDataSource(var response: Response<NetworkResponseModel>): ApiService {
    override suspend fun getItemsList(): Response<NetworkResponseModel> {
        return response
    }
}