package com.example.footbal360.data

import com.example.footbal360.data.model.sections.AllPosts
import retrofit2.http.GET
import retrofit2.http.Query

interface FootballApi {

    @GET("news-new")
    suspend fun getAllPosts(): AllPosts

    /*
    https://football360.ir/api/cms/v2/sections/page/news-new/?key=highlights
    "key": "highlights",
    "key": "recent domestic news",
    "key": "recent foreign news",
    "key": "popular news",
    "key": "recent news",
     */
    @GET("new-new")
    suspend fun getPostsBySection(@Query("key") key: String): AllPosts


    /*
    https://football360.ir/api/cms/v2/sections/page/explorer/?order_type=m&limit=5

    ?order_type=m&limit=5
     */
    @GET("explorer/")
    suspend fun getExplorerPosts(@Query("order_type") orderType:String = "m",
                                 @Query("limit") limit:String = "5"):AllPosts

    companion object {
        const val BASE_URL = "https://football360.ir/api/cms/v2/sections/page/"
        const val HIGHLIGHTS = "highlights"
        const val FOREIGN = "recent domestic news"
        const val DOMESTIC = "recent foreign news"
        const val POPULAR = "popular news"
        const val RECENT = "recent news"
    }
}