package com.example.footbal360.data

import com.example.footbal360.data.model.chips.Chips
import com.example.footbal360.data.model.sections.AllPosts
import com.example.footbal360.data.model.story.Stories
import retrofit2.http.GET
import retrofit2.http.Query

interface FootballApi {

    @GET("cms/v2/sections/page/news-new")
    suspend fun getAllPosts(): AllPosts

    /*
    https://football360.ir/api/cms/v2/sections/page/news-new/?key=highlights
    "key": "highlights",
    "key": "recent domestic news",
    "key": "recent foreign news",
    "key": "popular news",
    "key": "recent news",
     */
    @GET("cms/v2/sections/page/new-new")
    suspend fun getPostsBySection(@Query("key") key: String): AllPosts


    /*
    https://football360.ir/api/cms/v2/sections/page/explorer/?order_type=m&limit=5

    ?order_type=m&limit=5
     */
    @GET("cms/v2/sections/page/explorer/")
    suspend fun getSliderPosts(@Query("order_type") orderType:String = "m",
                               @Query("limit") limit:String = "5"):AllPosts

    /*
    https://football360.ir/api/story/category/
    */
    @GET("story/category/")
    suspend fun getStories() :Stories

    companion object {
        const val BASE_URL = "https://football360.ir/api/"
        const val HIGHLIGHTS = "highlights"
        const val FOREIGN = "recent domestic news"
        const val DOMESTIC = "recent foreign news"
        const val POPULAR = "popular news"
        const val RECENT = "recent news"
    }

    /*
    https://football360.ir/api/cms/v2/chips/baraye-shoma/items/
     */
    @GET("cms/v2/chips/baraye-shoma/items/")
    suspend fun getYoursChips():Chips


    /*
    https://football360.ir/api/cms/v2/chips/jadidtarinha/items/
     */
    @GET("cms/v2/chips/jadidtarinha/items/")
    suspend fun getNewsChips():Chips
}