package com.salvador.myapplication.ui.screens.detail_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.salvador.myapplication.domain.ItemRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class DetailViewModel @Inject constructor(
   private val itemRepository: ItemRepository
): ViewModel(){
    private val _detailUiState = MutableStateFlow(DetailUiState(loading = true))
    val detailUiState: StateFlow<DetailUiState> = _detailUiState.asStateFlow()

     fun getItem(key: String) = viewModelScope.launch {
        itemRepository.getSpecificItem(key = key).collectLatest { item ->
            _detailUiState.emit(DetailUiState(loading = false, item = item))
        }
    }
}