package com.salvador.myapplication.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun Image(href: String, modifier: Modifier, contentDescription: String) {
    SubcomposeAsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(href)
            .build(),
        modifier = modifier,
        loading = {
            CircularProgressIndicator()
        },
        contentDescription = contentDescription,
    )
}

@Composable
fun RegularCard(
    modifier: Modifier,
    color: Color = Color.LightGray,
    contentColor: Color = Color.Black,
    content: @Composable () -> Unit,
) {
    Card(
        backgroundColor = color,
        contentColor = contentColor,
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
    ) {
        Column(
            modifier = modifier
                .padding(start = 16.dp, end = 16.dp)
        ) {
            content()
        }
    }
}

fun String.toDate(
    dateFormat: String = "yyyy-MM-dd'T'HH:mm:ss'Z'",
    timeZone: TimeZone = TimeZone.getTimeZone("UTC")
): Date? {
    val parser = SimpleDateFormat(dateFormat, Locale.getDefault())
    parser.timeZone = timeZone
    return parser.parse(this)
}

fun Date.formatTo(
    dateFormat: String,
    timeZone: TimeZone = TimeZone.getDefault()
): String {
    val formatter = SimpleDateFormat(dateFormat, Locale.getDefault())
    formatter.timeZone = timeZone
    return formatter.format(this)
}