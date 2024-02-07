package com.example.footbal360.data.model.sections

data class AllPosts(
    val count: Int,
    val `data`: List<Data>,
    val limit: Int,
    val offset: Int
)