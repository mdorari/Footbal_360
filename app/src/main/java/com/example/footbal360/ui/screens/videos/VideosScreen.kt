package com.example.footbal360.ui.screens.videos

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.footbal360.R
import com.example.footbal360.ui.theme.MainTextColor
import com.example.footbal360.ui.theme.h2TextSize
import com.example.footbal360.ui.theme.startingPadding
import com.example.footbal360.ui.theme.subtitleLineHeight
import com.example.footbal360.ui.theme.subtitleTextSize
import com.example.footbal360.ui.theme.titleLineHeight
import com.example.footbal360.ui.theme.titleTextSize
import com.example.footbal360.ui.widget.SliderPost

@Composable
fun VideosScreen(
    paddingValues: PaddingValues,
    videosViewModel: VideosViewModel,
    navController: NavHostController
) {

    val videosData = videosViewModel.videos.collectAsState().value


    if (videosData.data.isEmpty()) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        LazyColumn(modifier = Modifier
            .fillMaxWidth()
            .padding(paddingValues = paddingValues)) {
            val videosSections = videosData.data
            items(videosSections) { section ->
                Text(
                    modifier = Modifier
                        .padding(top = 10.dp, bottom = 10.dp, end = startingPadding * 2)
                        .fillMaxWidth(),
                    text = section.title,
                    fontFamily = FontFamily(Font(R.font.iran_sansx_bold)),
                    fontSize = h2TextSize,
                    lineHeight = h2TextSize,
                    color = MainTextColor,
                    textAlign = TextAlign.Start,
                    style = TextStyle(textDirection = TextDirection.Content)

                )
                LazyRow(modifier = Modifier.height(180.dp), reverseLayout = true) {
                    val videoPosts = section.posts
                    items(videoPosts) { post ->
                        SliderPost(post = post,
                            width = 200.dp,
                            textSize = subtitleTextSize,
                            textLineHeight = subtitleLineHeight,
                            cornerRadius = 6.dp)
                    }
                }
            }
        }
    }
}