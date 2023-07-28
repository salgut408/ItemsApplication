package com.salvador.myapplication.ui.screens.main_screen

import com.salvador.myapplication.domain.ItemDomainModel

data class ListUiState(
    val items: List<ItemDomainModel> = listOf(),
    val loading: Boolean = true
)
