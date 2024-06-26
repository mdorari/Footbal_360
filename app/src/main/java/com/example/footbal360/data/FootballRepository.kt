package com.example.footbal360.data

import com.example.footbal360.data.model.chips.Chips
import com.example.footbal360.data.model.sections.AllPosts
import com.example.footbal360.data.model.story.Stories
import com.example.footbal360.data.model.videoPost.VideoPost
import kotlinx.coroutines.flow.Flow

interface FootballRepository {
//    suspend fun getAllPosts():Flow<Result<AllPosts>>

//    suspend fun getPostsBySection(key:String):Flow<Result<AllPosts>>

    suspend fun getSliderPosts():Flow<Result<AllPosts>>

    suspend fun getStories():Flow<Result<Stories>>

    suspend fun getYourChips():Flow<Result<Chips>>

    suspend fun getBottomSheetPosts():Flow<Result<AllPosts>>

    suspend fun getAllVideosPosts():Flow<Result<AllPosts>>

    suspend fun getPostByPostCode(postCode:Int):Flow<Result<VideoPost>>
}