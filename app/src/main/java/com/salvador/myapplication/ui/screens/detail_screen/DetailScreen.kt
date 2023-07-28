package com.salvador.myapplication.ui.screens.detail_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.salvador.myapplication.ui.screens.Image
import com.salvador.myapplication.ui.screens.formatTo
import com.salvador.myapplication.ui.screens.toDate

@Composable
fun DetailScreen(
    detailViewModel: DetailViewModel = hiltViewModel(),
    itemDesc: String,
    modifier: Modifier,
) {
    detailViewModel.getItem(itemDesc)
    val detailUiState by detailViewModel.detailUiState.collectAsState()
    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            TopAppBar() {
                Text(text = detailUiState.item.title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp)
            }
        },
    ) {
        if (detailUiState.loading) {
            CircularProgressIndicator()
        } else {
            Column(
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp)
                    .verticalScroll(state = scrollState)

            ) {
                    Text(text = detailUiState.item.title,
                        style = MaterialTheme.typography.h3)

                Text(text = detailUiState.item.date.toDate()?.formatTo("MM/dd/yyy") ?: "")
                Image(
                    href = detailUiState.item.img,
                    modifier = modifier.fillMaxWidth(),
                    contentDescription = detailUiState.item.title
                )
                Divider(
                    color = Color.Black,
                    thickness = 2.dp,
                    modifier = modifier.padding(top = 8.dp, bottom = 8.dp)
                )
                Text(text = detailUiState.item.description)
            }
        }
    }
}




