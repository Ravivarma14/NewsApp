package com.example.newsapp.repository

import com.example.newsapp.Repository.NewsRepository
import com.example.newsapp.data.Article
import com.example.newsapp.data.NewsResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class NewsRepositoryTest {
    private lateinit var repository: NewsRepository

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        repository = NewsRepository()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `getTopHeadlines returns articles on success`() = runTest {
        val mockArticles = listOf(
            Article(title = "Test 1", description = "Desc 1","","","","",""),
            Article(title = "Test 2", description = "Desc 2","","","","","")
        )
        val response = NewsResponse("ok",2, mockArticles)
        val apiKey = "c9d18c261a3d45b3af9e948cbcf9ba36"

        whenever(repository.getTopHeadlines("us", apiKey)).thenReturn(response.articles)

        val result = repository.getTopHeadlines("us", apiKey)

        assertNotNull(result)
        assertEquals(2, result?.size)
        assertEquals("Test 1", result?.get(0)?.title)
    }

    @Test
    fun `getTopHeadlines returns null on failure`() = runTest {
        val apiKey = "c9d18c261a3d45b3af9e948cbcf9ba36"
        whenever(repository.getTopHeadlines("us", apiKey)).thenThrow(RuntimeException("API Error"))

        val result = repository.getTopHeadlines("us", apiKey)

        assertNull(result)
    }

}
