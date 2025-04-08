package com.example.newsapp.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.newsapp.Repository.NewsRepository
import com.example.newsapp.data.Article
import com.example.newsapp.viewModels.NewsViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.*
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever
import org.junit.Assert.*

@ExperimentalCoroutinesApi
class NewsViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var repository: NewsRepository
    private lateinit var viewModel: NewsViewModel
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)

        repository = mock(NewsRepository::class.java)
        viewModel = NewsViewModel(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `fetchTopHeadlines updates LiveData with articles`() = runTest {
        // Arrange
        val country = "us"
        val apiKey = "c9d18c261a3d45b3af9e948cbcf9ba36"
        val mockArticles = listOf(
            Article(title = "Test Title 1", description = "Description 1","","","","",""),
            Article(title = "Test Title 2", description = "Description 2","","","","","")
        )

        whenever(repository.getTopHeadlines(country, apiKey)).thenReturn(mockArticles)

        // Act
        viewModel.fetchTopHeadlines(country,apiKey)
        testDispatcher.scheduler.advanceUntilIdle()

        // Assert
        val result = viewModel.articles.value
        assertNotNull(result)
        assertEquals(2, result?.size)
        assertEquals("Test Title 1", result?.get(0)?.title)
    }
}
