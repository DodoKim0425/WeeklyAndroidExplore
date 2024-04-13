package com.dodo.typeadaptersample

import android.util.Log
import com.dodo.hilttestapp.network.TestRepository
import com.dodo.typeadaptersample.data.MyEmail
import com.dodo.typeadaptersample.data.TestBody
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.Date
import javax.inject.Inject

private const val TAG = "SampleTest"
@HiltAndroidTest
class SampleTest {
    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var repository: TestRepository

    @Before
    fun beforeFunction(){
        hiltRule.inject()
    }

    @Test
    fun testGetDob() = runBlocking{
        val result = repository.getDob()
        if(result.isSuccessful){
            val formatResult = result.body()?.results?.get(0)?.dob?.date
            val isResultTypeDate = formatResult is Date
            Log.d(TAG, "testGetDob: ${result.body()?.results?.get(0)?.dob}")
            assertThat(isResultTypeDate).isTrue()
        }else{
            assertThat(false).isTrue()
        }
    }

    @Test
    fun testGetDob2() = runBlocking {
        repository.getDob2(Date())
        assertThat(true).isTrue()
    }

    @Test
    fun testPostDob() = runBlocking {
        val testBody = TestBody(
            Date()
        )
        repository.postDob(testBody)
        assertThat(true).isTrue()
    }
    
    @Test
    fun testGetEmail() = runBlocking {
        val result = repository.getEmail()
        if(result.isSuccessful){
            val formatResult = result.body()?.results?.get(0)?.email
            val isResultTypeMyEmail = formatResult is MyEmail

            Log.d(TAG, "testGetEmail: ${formatResult?.userId} / ${formatResult?.emailDomain}")
            
            assertThat(isResultTypeMyEmail).isTrue()
        }else{
            assertThat(false).isTrue()
        }
    }
}