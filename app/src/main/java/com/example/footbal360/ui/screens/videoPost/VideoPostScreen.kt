package com.example.footbal360.ui.screens.videoPost

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavHostController
import com.example.footbal360.data.model.sections.Post


@Composable
fun VideoPostScreen(
    navController: NavHostController,
    videoPostScreenViewModel: VideoPostScreenViewModel
) {

    val postData = videoPostScreenViewModel.videoPost.collectAsState().value
    Column {
        if (postData.data.isNotEmpty()){
            val post = postData.data.first().posts.first()
            if (post != null) {
                Box {
                    Text(text = post.title)
                }
                Text(text = post.primary_media.upload_video_link)
            }
        }else{
            Box {
                Text(text = "Loading Error")
            }
        }
    }
    
}