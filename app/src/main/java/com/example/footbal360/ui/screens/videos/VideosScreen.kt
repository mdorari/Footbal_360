package com.example.footbal360.ui.screens.videos

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.footbal360.R
import com.example.footbal360.ui.theme.MainTextColor
import com.example.footbal360.ui.theme.h2TextSize
import com.example.footbal360.ui.theme.startingPadding
import com.example.footbal360.ui.theme.subtitleLineHeight
import com.example.footbal360.ui.theme.subtitleTextSize
import com.example.footbal360.ui.widget.VideoPost
import com.example.footbal360.ui.widget.shimmerEffect
import com.example.footbal360.util.UiEvent

@Composable
fun VideosScreen(
    onNavigate: (UiEvent.Navigate) -> Unit,
    paddingValues: PaddingValues,
    videosViewModel: VideosViewModel = hiltViewModel(),
    navController: NavHostController
) {

    val videosData = videosViewModel.videos.collectAsState().value

    val isLoading by remember {
        mutableStateOf(true)
    }

    LaunchedEffect(key1 = true) {
        videosViewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.Navigate -> onNavigate(event)
                else -> Unit
            }
        }
    }


    if (videosData.data.isEmpty()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingValues = paddingValues),
            horizontalAlignment = Alignment.End
        ) {
            items(8) {
                Box(
                    modifier = Modifier
                        .padding(horizontal = 5.dp)
                        .fillMaxWidth(0.15f)
                        .height(20.dp)
                        .clip(RoundedCornerShape(3.dp))
                        .shimmerEffect()
                )
                LazyRow(
                    modifier = Modifier
                        .height(180.dp),
                    reverseLayout = true
                ) {
                    items(5) {
                        Column(
                            modifier = Modifier
                                .width(200.dp)
                                .padding(8.dp),
                            horizontalAlignment = Alignment.End
                        ) {
                            Box(
                                modifier = Modifier
                                    .padding(vertical = 2.dp)
                                    .aspectRatio(1.78f)
                                    .height(200.dp)
                                    .clip(RoundedCornerShape(8.dp))
                                    .shimmerEffect()
                            )
                            Box(
                                modifier = Modifier
                                    .padding(vertical = 2.dp)
                                    .fillMaxWidth()
                                    .height(20.dp)
                                    .clip(RoundedCornerShape(3.dp))
                                    .shimmerEffect()
                            )
                            Box(
                                modifier = Modifier
                                    .padding(vertical = 2.dp)
                                    .fillMaxWidth(.45f)
                                    .height(20.dp)
                                    .clip(RoundedCornerShape(3.dp))
                                    .shimmerEffect()

                            )
                        }
                    }
                }


            }
        }
//        Box(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(paddingValues = paddingValues),
//            contentAlignment = Alignment.Center
//        ) {
//            CircularProgressIndicator()
//        }
    } else {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingValues = paddingValues)
        ) {
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
                        VideoPost(
                            post = post,
                            width = 200.dp,
                            textSize = subtitleTextSize,
                            textLineHeight = subtitleLineHeight,
                            cornerRadius = 6.dp,
                            onEvent = videosViewModel::onEvent
                        )
//                        {
//                            navController.navigate(route = Routes.VIDEO_POST + "/${it.code}")
//                        }
                    }
                }
            }
        }
    }
}