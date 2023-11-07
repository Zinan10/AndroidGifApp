package com.gifs.app.user.api

import com.gifs.app.user.util.AppConstant
import com.gifs.app.user.util.Helper
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NewsApiTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var api: ApiService

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        api = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(ApiService::class.java)
    }

    @Test
    fun testGetGifs_ReturnEmptyResponse() = runBlocking {
        val mockResponse = MockResponse()
        mockResponse.setBody("{}")
        mockWebServer.enqueue(mockResponse)

        val response = api.getGifs(AppConstant.API_KEY,"")
        mockWebServer.takeRequest()
        Assert.assertEquals(null, response.data)
    }

    @Test
    fun testGetGifs_returnGifs() = runBlocking {
        val mockResponse = MockResponse()
        val content = Helper.readFileResource("/test.json")
        mockResponse.setResponseCode(200)
        mockResponse.setBody(content)
        mockWebServer.enqueue(mockResponse)

        val response = api.getGifs(AppConstant.API_KEY,"cats")
        mockWebServer.takeRequest()
        Assert.assertEquals(true, response.data.isNotEmpty())
        Assert.assertEquals(1, response.data.size)
    }

    @Test
    fun testGetGifs_returnError() = runBlocking {
        val mockResponse = MockResponse()
        mockResponse.setBody(Helper.returnError())
        mockWebServer.enqueue(mockResponse)

        val response = api.getGifs("cf","cats")
        mockWebServer.takeRequest()
        Assert.assertEquals("Unauthorized", response.meta.msg)
    }


    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }
}