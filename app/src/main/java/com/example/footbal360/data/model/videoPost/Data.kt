package com.example.footbal360.data.model.videoPost

data class Data(
    val code: Int,
    val created_at: Int,
    val id: String,
    val link: String,
    val post_type: String,
    val primary_media: PrimaryMedia,
    val published_at: Int,
    val slug: String,
    val sub_title: String,
    val super_title: Any,
    val title: String
)