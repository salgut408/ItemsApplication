package com.salvador.myapplication.ui.screens.main_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.salvador.myapplication.domain.ItemDomainModel
import com.salvador.myapplication.domain.ItemRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val itemRepository: ItemRepository,
) : ViewModel() {

    var _itemsListUiState = MutableStateFlow(ListUiState(loading = true))
    val itemsListUiState: StateFlow<ListUiState> = _itemsListUiState.asStateFlow()
    var emailValid = false

    init {
        saveToDatabase()
        loadItems()
        println(palidrome("cat"))
    }

    private fun loadItems() = viewModelScope.launch {
        itemRepository.getAllItems().collectLatest { allItems ->
            _itemsListUiState.emit(ListUiState(loading = false, items = allItems))
        }
    }

    private fun saveToDatabase() = viewModelScope.launch {
        itemRepository.saveItems()
    }


    fun isEmailValid(email: String): Boolean {
        val emailRegex = Regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")
        emailValid = emailRegex.matches(email)
        return emailRegex.matches(email)
    }

    fun palidrome(str1: String): Boolean {
        val charArray = str1.toCharArray()
        var left = 0
        var right = charArray.size - 1
        while (left < right) {
            if (charArray[left] != charArray[right]) {
                return false
            }
            left++
            right--
        }
        return true
    }

}


data class StatsResult(val itemsWithImagePercent: Float, val itemsWithOutImage: Float)