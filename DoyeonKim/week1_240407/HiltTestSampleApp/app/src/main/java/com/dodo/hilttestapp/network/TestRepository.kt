package com.dodo.hilttestapp.network

import com.dodo.hilttestapp.Post
import retrofit2.Response
import retrofit2.Retrofit

class TestRepository (private val retrofit: Retrofit, private val testService: TestService) {
    suspend fun getPost(): Response<Post> {
        return testService.getFirstPost()
    }
}