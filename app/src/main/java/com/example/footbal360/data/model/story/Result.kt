package com.example.footbal360.data.model.story

data class Result(
    val icon: String,
    val id: String,
    val key: String,
    val last_story: LastStory,
    val number_of_stories: Int,
    val order: Int,
    val title: String
)