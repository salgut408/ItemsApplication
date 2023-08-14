package com.salvador.myapplication.ui.screens.main_screen

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.salvador.myapplication.data.FakeRepo
import com.salvador.myapplication.data.ItemRepositoryImpl
import com.salvador.myapplication.data.api.ApiService
import com.salvador.myapplication.data.database.ItemDao
import com.salvador.myapplication.domain.ItemRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.*
import org.junit.*
import org.junit.Assert.*
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import javax.inject.Inject


@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class MainScreenViewModelTest {
    private lateinit var viewModel: MainScreenViewModel
    private val testDispatcher = StandardTestDispatcher()
    private lateinit var repositoryToTestRepo: ItemRepository


    @Mock
    lateinit var itemDao: ItemDao

    @Mock
    lateinit var fakeApi: ApiService

    @Mock
    private lateinit var fakeRepo: ItemRepository


    @get: Rule
    var rule = InstantTaskExecutorRule()



    @Before
    fun setUp(){
        MockitoAnnotations.openMocks(this)

        Dispatchers.setMain(testDispatcher)
        fakeRepo = ItemRepositoryImpl(fakeApi,itemDao)
        viewModel = MainScreenViewModel(fakeRepo)
    }

    @After
    fun tearDown(){
        Dispatchers.resetMain()
    }



    @Test
    fun `test fetching data updates UI state`() = runTest(UnconfinedTestDispatcher()) {
        val expectedUiState = ListUiState(loading = true, items = listOf())
        viewModel.itemsListUiState.take(1).collect{
            assertEquals(expectedUiState, it)
        }
    }

    @Test
    fun isUiStatePopulated_yes() = runBlocking{
        Dispatchers.setMain(testDispatcher)
        val expectedUiState = ListUiState(loading = true, items = listOf())

        viewModel = MainScreenViewModel(fakeRepo)
        viewModel.itemsListUiState.take(1).collect{
           assertEquals(expectedUiState, it)
        }
    }

    @Test
    fun isEmailValid_test_yes() {
        viewModel.isEmailValid("user@gmail.com")
        assertEquals(viewModel.emailValid, true)
    }
    @Test
    fun isEmailValid_test_no() {
        viewModel.isEmailValid("user")
        assertEquals(viewModel.emailValid, false)
    }


}