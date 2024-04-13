package com.dodo.typeadaptersample.di

import com.dodo.hilttestapp.network.TestRepository
import com.dodo.hilttestapp.network.TestService
import com.dodo.typeadaptersample.data.MyEmail
import com.dodo.typeadaptersample.network.DateAdapter
import com.dodo.typeadaptersample.network.EmailAdapter
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.Date
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private val logging = HttpLoggingInterceptor().apply {
        // 요청과 응답의 본문 내용까지 로그에 포함
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

    @Provides
    fun provideGson(): Gson{
        return GsonBuilder()
            .setLenient()
            // Date 라는 클래스를 찾으면 DateAdapter로 직렬화, 역직렬화 한다
            .registerTypeAdapter(Date::class.java , DateAdapter())
            //.setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
            .registerTypeAdapter(MyEmail::class.java, EmailAdapter())
            .create()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit {

        return Retrofit.Builder()
            .baseUrl("https://randomuser.me/")
            .client(okHttpClient)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Provides
    @Singleton
    fun provideTestService(retrofit: Retrofit): TestService {
        return retrofit.create(TestService::class.java)
    }

    @Provides
    @Singleton
    fun provideAppServiceRepository(
        retrofit: Retrofit,
        testService: TestService
    ): TestRepository {
        return TestRepository(retrofit, testService)
    }
}