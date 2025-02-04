package org.sopt.official.data.service.main

import org.sopt.official.data.model.main.MainViewResponse
import retrofit2.http.GET

interface MainViewService {
    @GET("user/main")
    suspend fun getMainView() : MainViewResponse
}