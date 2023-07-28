package com.salvador.myapplication.ui.screens.main_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

    private val _itemsListUiState = MutableStateFlow(ListUiState(loading = true))
    val itemsListUiState: StateFlow<ListUiState> = _itemsListUiState.asStateFlow()

    init {
        saveToDatabase()
        loadItems()
    }

    private fun loadItems() = viewModelScope.launch {
        itemRepository.getAllItems().collectLatest { allItems ->
            _itemsListUiState.emit(ListUiState(loading = false, items = allItems))
        }
    }

    private fun saveToDatabase() = viewModelScope.launch {
        itemRepository.saveItems()
    }
}


