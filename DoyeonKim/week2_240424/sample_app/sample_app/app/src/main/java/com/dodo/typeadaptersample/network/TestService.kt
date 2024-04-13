package com.dodo.hilttestapp.network

import com.dodo.typeadaptersample.data.GetDobResult
import com.dodo.typeadaptersample.data.GetEmailResult
import com.dodo.typeadaptersample.data.TestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import java.util.Date

interface TestService {
    @GET("api/")
    suspend fun getDob(
        @Query("inc") inc: String
    ): Response<GetDobResult>

    @GET("api/")
    suspend fun getDob2(
        @Query("test") test: Date
    )

    @POST("api/")
    suspend fun postDob(
        @Body testData : TestBody
    )

    @GET("api/")
    suspend fun getEmail(
        @Query("inc") inc: String
    ): Response<GetEmailResult>
}