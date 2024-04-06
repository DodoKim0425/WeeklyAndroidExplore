package com.dodo.hilttestapp

import com.google.common.truth.Truth.assertThat
import android.util.Log
import com.dodo.hilttestapp.network.TestRepository
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.AfterClass
import org.junit.Assert
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import kotlin.math.log

private const val TAG = "ApiTest"

@HiltAndroidTest
class ApiTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var repository: TestRepository

    companion object{
        @BeforeClass
        @JvmStatic
        fun beforeClassTest() {
            Log.d(TAG, "beforeClassTest: ")

        }
        @AfterClass
        @JvmStatic
        fun afterClassTest() {
            Log.d(TAG, "afterClassTest: ")
        }
    }


    @Before
    fun beforeTest() {
        Log.d(TAG, "before: ")
        hiltRule.inject() // 주입을 위해서 필요함
        // 의존성 주입이나 혹은 api 요청 전 필요 작업들을 하면된다(엑세스토큰 설정 등등)
    }

    @Test(timeout = 5000)
    fun test1() = runBlocking {
        Log.d(TAG, "Test1: ")
        val testval: Int = 1
        assertThat(testval).isEqualTo(15)
    }

    @Test
    fun test2() = runBlocking {
        Log.d(TAG, "Test2: ")
        assertThat(true).isTrue()
    }
    
    @Test
    fun test3() = runBlocking { 
        val result = repository.getPost()
        if(result.isSuccessful){
            Log.d(TAG, "test3: ${result.body()}")
            assertThat(result.body()).isNotNull()
        }else{
            //무조건 실패 처리
            assertThat(false).isTrue()
        }
    }

    @After
    fun afterTest() = runBlocking {
        Log.d(TAG, "after: ")
        assertThat(true).isTrue()
    }
}