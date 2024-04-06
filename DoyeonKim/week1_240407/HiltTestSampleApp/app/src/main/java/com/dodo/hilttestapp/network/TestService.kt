package com.dodo.hilttestapp.network

import com.dodo.hilttestapp.Post
import retrofit2.Response
import retrofit2.http.GET

interface TestService {
    @GET("posts/1")
    suspend fun getFirstPost(): Response<Post>
}