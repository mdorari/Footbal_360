package com.example.footbal360.ui.screens.videoPost

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.footbal360.ui.widget.shimmerEffect
import com.example.footbal360.util.UiEvent


@Composable
fun VideoPostScreen(
    onNavigate: (UiEvent.Navigate) -> Unit,
    paddingValues: PaddingValues,
//    navController: NavHostController,
    videoPostScreenViewModel: VideoPostScreenViewModel = hiltViewModel()
) {

    val postData = videoPostScreenViewModel.videoPost.collectAsState().value
    Log.d("Mehrdad", "VideoPostScreen: $postData")

    LaunchedEffect(key1 = true) {
        videoPostScreenViewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.Navigate -> onNavigate(event)
                else -> Unit
            }
        }
    }

    Box(modifier = Modifier.padding(paddingValues = paddingValues)) {
        if (postData.data.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(PaddingValues())
                    .shimmerEffect()
            )
        } else {
            Column(modifier = Modifier.padding(PaddingValues())) {
//            if (postData.data.isNotEmpty()) {
                val post = postData.data.first()
                Box {
                    Text(text = post.title)
                }
                Text(text = post.primary_media.upload_video_link ?: "There is no video")
                //            } else {
//                Box {
//                    Text(text = "Loading Error")
//                }
//            }
            }
        }
    }
}