package com.salvador.myapplication.ui.screens.detail_screen

import com.salvador.myapplication.domain.ItemDomainModel

data class DetailUiState (
    val item: ItemDomainModel = ItemDomainModel(),
    val loading: Boolean = true
)