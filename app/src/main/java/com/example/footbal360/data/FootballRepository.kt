package com.example.footbal360.data

import com.example.footbal360.data.model.sections.AllPosts
import kotlinx.coroutines.flow.Flow

interface FootballRepository {
    suspend fun getAllPosts():Flow<Result<AllPosts>>

    suspend fun getPostsBySection(key:String):Flow<Result<AllPosts>>

    suspend fun getExplorerPosts():Flow<Result<AllPosts>>
}