package com.example.footbal360.data

import com.example.footbal360.data.model.chips.Chips
import com.example.footbal360.data.model.sections.AllPosts
import com.example.footbal360.data.model.story.Stories
import com.example.footbal360.data.model.videoPost.VideoPost
import retrofit2.http.GET
import retrofit2.http.Query

interface FootballApi {


    /*
    https://football360.ir/api/cms/v2/sections/page/explorer/?order_type=m&limit=5

    ?order_type=m&limit=5
     */
    @GET("cms/v2/sections/page/explorer/")
    suspend fun getBottomSheetPosts(@Query("order_type") orderType:String = "m",
                               @Query("limit") limit:String = "20"):AllPosts
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
                               @Query("limit") limit:String = "1"):AllPosts

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

    /*
    https://football360.ir/api/cms/v2/chips/videos/items/
     */
    @GET("cms/v2/chips/videos/items/")
    suspend fun getVideosChips():Chips


    /*
    https://football360.ir/api/cms/sections/?page=ekhtesasi
     */
    @GET("cms/sections/?page=ekhtesasi")
    suspend fun getEkhtesasiVideos():AllPosts

    /*
    https://football360.ir/api/cms/v2/sections/page/videos-new/?order_type=m&limit=50
     */
    @GET("cms/v2/sections/page/videos-new/?order_type=m&limit=50")
    suspend fun getAllVideos():AllPosts


    /*
    https://football360.ir/api/cms/v2/posts/?code=2024021524
     */
    @GET("cms/v2/posts/")
    suspend fun getPostByPostCode(@Query("code") code:Int):VideoPost







}