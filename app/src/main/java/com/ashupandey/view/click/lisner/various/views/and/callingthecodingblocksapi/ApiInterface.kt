package com.ashupandey.view.click.lisner.various.views.and.callingthecodingblocksapi

import Data
import DataItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("users")
    suspend fun getData(
     @Query("page") page : Int = 1
    ):Response<DataItem>?
}



