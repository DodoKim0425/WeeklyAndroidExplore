package com.dodo.hilttestapp.network

import com.dodo.typeadaptersample.data.GetDobResult
import com.dodo.typeadaptersample.data.GetEmailResult
import com.dodo.typeadaptersample.data.TestBody
import retrofit2.Response
import retrofit2.Retrofit
import java.util.Date

class TestRepository (private val retrofit: Retrofit, private val testService: TestService) {
    suspend fun getDob(): Response<GetDobResult> {
        return testService.getDob(
            inc = "dob"
        )
    }
    suspend fun getDob2(date: Date){
        testService.getDob2(date)
    }
    suspend fun postDob(testData: TestBody){
        testService.postDob(testData)
    }
    suspend fun getEmail(): Response<GetEmailResult>{
        return testService.getEmail(
            inc = "email"
        )
    }
}