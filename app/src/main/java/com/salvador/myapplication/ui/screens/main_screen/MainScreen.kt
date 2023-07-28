package com.salvador.myapplication.ui.screens.main_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.salvador.myapplication.domain.ItemDomainModel
import com.salvador.myapplication.ui.navigation.NavigationScreens
import com.salvador.myapplication.ui.screens.Image
import com.salvador.myapplication.ui.screens.RegularCard
import com.salvador.myapplication.ui.screens.formatTo
import com.salvador.myapplication.ui.screens.toDate


@Composable
fun MainScreen(
    navController: NavController,
    mainScreenViewModel: MainScreenViewModel = hiltViewModel(),
    modifier: Modifier,
) {
    val listState by mainScreenViewModel.itemsListUiState.collectAsState()

    Column(
        modifier =modifier
    ) {
        ItemList(
            items = listState.items,
            modifier = modifier,
            navController = navController
        )
    }
}

@Composable
fun ItemList(
    items: List<ItemDomainModel>,
    modifier: Modifier,
    navController: NavController,
) {
    LazyColumn {
        items(items) { item ->
            Item(
                img = item.img ,
                description = item.description,
                title = item.title,
                date = item.date,
                modifier = modifier,
                navController = navController
            )
        }
    }
}

@Composable
fun Item(
    title: String,
    date: String,
    img: String,
    description: String,
    modifier: Modifier,
    navController: NavController,
    ) {
    RegularCard(
        modifier = modifier
            .padding(8.dp)
            .clickable {
                navController.navigate(
                    NavigationScreens.DetailScreen.withArgs(description)
                )
            },
        content = {
            Text(text = title, style = MaterialTheme.typography.h3)
            Text(text = date.toDate()?.formatTo("MM/dd/yyy") ?: "")
            Image(href = img , modifier = modifier, contentDescription = title )
        }
    )
}