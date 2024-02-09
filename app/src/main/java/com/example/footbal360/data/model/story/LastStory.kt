package com.example.footbal360.data.model.story

data class LastStory(
    val button_text: String,
    val created_at: Int,
    val description: String,
    val expired_at: Int,
    val hour_duration: Int,
    val id: String,
    val image: String,
    val shortcut_address: String,
    val shortcut_full_address: String,
    val shortcut_id: String,
    val story_type: String,
    val video: Video
)