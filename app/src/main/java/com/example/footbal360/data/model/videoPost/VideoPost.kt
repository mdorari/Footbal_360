package com.example.footbal360.data.model.videoPost

data class VideoPost(
    val count: Int,
    val `data`: List<Data>,
    val limit: Int,
    val offset: Int
)